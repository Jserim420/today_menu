package com.example.today_menu.menuPick;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MenuResultActivity extends AppCompatActivity {

    FirebaseFirestore fireStore;

    String foodCountry ;
    String foodCategory ;
    String foodTaste ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuresult);

        fireStore = FirebaseFirestore.getInstance();
        TextView resultTv = findViewById(R.id.result_tv);

        foodCountry = getIntent().getStringExtra("country");
        foodCategory = getIntent().getStringExtra("category");
        foodTaste = getIntent().getStringExtra("taste");


        fireStore.collection("Food")
                .whereEqualTo(foodCountry, true)
                .whereEqualTo(foodCategory, true)
                .whereEqualTo(foodTaste, true)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d("MenuResultActivity", "OnSuccess");
                        for(DocumentSnapshot item : queryDocumentSnapshots) {
                            String result = item.get("name").toString();
                            Log.d("MenuResultActivity", result);

                            resultTv.setText(result);
                        }
                        Log.d("MenuResultActivity", "OnSuccess_for");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MenuResultActivity", e.getMessage());
                    }
                });



    }

}
