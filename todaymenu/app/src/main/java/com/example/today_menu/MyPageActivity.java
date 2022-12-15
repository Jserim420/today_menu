package com.example.today_menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyPageActivity extends AppCompatActivity implements HistoryFoodAdapter.OnHistoryClickListener {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    ArrayList<FoodHistory> mHistoryFoodList = new ArrayList<>();
    HistoryFoodAdapter mHistoryFoodAdapter;
    FirebaseAuth firebaseAuth ;
    List<ProfileImg> mProfileImgList = new ArrayList<>();
    ProfileImg profileImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        TextView userID = findViewById(R.id.useremail_tv);
        Button changePW = findViewById(R.id.ch_password_btn);
        Button deleteUserBtn = findViewById(R.id.delete_user_btn);
        ImageView profileImgIv = findViewById(R.id.myicon_iv);

        RecyclerView foodHistoryRv = findViewById(R.id.history_rv);
        firebaseAuth = FirebaseAuth.getInstance();

        mHistoryFoodAdapter = new HistoryFoodAdapter(mHistoryFoodList);
        mHistoryFoodAdapter.setOnHistoryClickListener(this);
        foodHistoryRv.setAdapter(mHistoryFoodAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        foodHistoryRv.setLayoutManager(layoutManager);

        userID.setText(firebaseAuth.getCurrentUser().getEmail());
        userID.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        String updateImage = getIntent().getStringExtra("ImgUrl");

        loadHistoryList();

        if(updateImage!=null) {
            Glide.with(profileImgIv.getContext()).load(updateImage).into(profileImgIv);
        }

        firestore.collection("Profile")
                .orderBy("uploadDate", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot snapshot : documents) {
                            if(snapshot.get("userId")!=null && snapshot.get("userId").equals(firebaseAuth.getCurrentUser().getEmail())) {
                                ProfileImg profileImg = snapshot.toObject(ProfileImg.class);
                                String id = snapshot.getId();
                                Log.d("Profile", id);
                                profileImg.setId(id);
                                mProfileImgList.add(profileImg);
                            }
                        }
                        if(mProfileImgList.size()>0) {
                            Glide.with(profileImgIv.getContext()).load(mProfileImgList.get(0).getImageUrl()).into(profileImgIv);
                        } else {
                            profileImgIv.setImageDrawable(getResources().getDrawable(R.drawable.user));
                        }
                        if(updateImage!=null) {
                            Glide.with(profileImgIv.getContext()).load(updateImage).into(profileImgIv);
                        }
                    }
                });




        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, ChangePwPopup.class);
                Log.d("Popup", "Change Password Popup");
                startActivityForResult(intent, 1);
            }
        });

        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickShowAlert(view);
            }
        });

        profileImgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, ImgPopup.class);
                startActivity(intent);
            }
        });
    }

    public void loadHistoryList() {
        firestore.collection("History")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                mHistoryFoodList.clear();
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot : documents) {
                    FoodHistory foodHistory = snapshot.toObject(FoodHistory.class);
                    String id = snapshot.getId();
                    Log.d("snapshotId", id);
                    foodHistory.setId(id);
                    mHistoryFoodList.add(foodHistory);
                }
                mHistoryFoodAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void onClickShowAlert(View view) {
        AlertDialog.Builder mAlert = new AlertDialog.Builder(MyPageActivity.this);
        mAlert.setTitle("경고");
        mAlert.setMessage("회원 탈퇴 하시겠습니까?");
        mAlert.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MyPageActivity.this, "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
                                firebaseAuth.signOut();
                                finish();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }
                        });
            }
        });

        mAlert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                Log.d("Result", "Complete Change Password Popup");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mAlert.show();
    }

    @Override
    public void onClick(FoodHistory foodHistory) {
        firestore.collection("History").document(foodHistory.getId()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        loadHistoryList();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MyPageActivity", e.getMessage());
                    }
                });
    }
}
