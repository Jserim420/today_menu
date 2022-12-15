package com.example.today_menu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class ImgPopup extends Activity {

    final int REQ_IMAGE_PICK = 1000;

    ImageView imageIv;

    Uri selectedImage; // 파일의 경로를 저장하는 변수형태

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    FirebaseStorage storage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_upload_img);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        ImageView imageIv = findViewById(R.id.image_iv);
        Button uploadBtn = findViewById(R.id.upload_btn);
        imageIv.setImageDrawable(getResources().getDrawable(R.drawable.add));


        imageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQ_IMAGE_PICK);

            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedImage == null) { //사용자가 이미지를 선택하지 않았을때
                    Toast.makeText(ImgPopup.this, "이미지를 선택해주세요", Toast.LENGTH_LONG).show();
                    return ;
                }

                String fileName = UUID.randomUUID().toString();
                storage.getReference().child("image").child(fileName)
                        .putFile(selectedImage)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                taskSnapshot.getMetadata().getReference().getDownloadUrl()
                                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                String imageUrl = uri.toString();
                                                Log.d("ImgPopup", imageUrl);
                                                ProfileImg profileImg = new ProfileImg();
                                                String userEmail = auth.getCurrentUser().getEmail();
                                                profileImg.setUserId(userEmail);
                                                profileImg.setImageUrl(imageUrl);

                                                uploadPost(profileImg);

                                                mOnClose(view, imageUrl);
                                            }
                                        });
                            }
                        });
            }
        });
    }

    public void uploadPost(ProfileImg profileImg) {
        firestore.collection("Profile").add(profileImg)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("uploadPost", "Success");
                    }
                });
    }

    public void mOnClose(View v, String imageUrl) {
        Intent intent = new Intent();
        intent.putExtra("ImgUrl", imageUrl);
        Log.d("Result", "Complete Change Password Popup");
        setResult(RESULT_OK, intent);

        finish();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_IMAGE_PICK && resultCode==RESULT_OK) {
            imageIv = findViewById(R.id.image_iv);
            selectedImage = data.getData();
            imageIv.setImageURI(selectedImage);
        }
    }
}
