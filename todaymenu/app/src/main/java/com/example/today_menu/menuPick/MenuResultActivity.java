package com.example.today_menu.menuPick;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.today_menu.Food;
import com.example.today_menu.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuResultActivity extends AppCompatActivity {


    ArrayList<Food> PerfectFoodList = new ArrayList<>();
    ArrayList<Food> NiceFoodList = new ArrayList<>();
    ArrayList<Food> GoodFoodList = new ArrayList<>();
    ArrayList<Food> FryFoodList = new ArrayList<>();


    FoodAdapter mFoodAdapter;

    String foodCountry ;
    String foodCategory ;
    String foodTaste ;

    int random = (int) Math.random();

    FirebaseFirestore fireStore=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuresult);

        fireStore = FirebaseFirestore.getInstance();
        TextView resultTv = findViewById(R.id.result_tv);

        foodCountry = getIntent().getStringExtra("FoodCountry");
        foodCategory = getIntent().getStringExtra("FoodCategory");
        foodTaste = getIntent().getStringExtra("FoodTaste");

        Button againBtn = findViewById(R.id.again_btn);

        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuResultActivity.this, StartQuestionActivity.class);
                startActivity(intent);
            }
        });

        loadFoodList(resultTv);
    }

    public void loadFoodList(TextView textView) {
        fireStore.collection("Food")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d("MenuResultActivity", foodCountry);
                        Log.d("MenuResultActivity", foodCategory);
                        Log.d("MenuResultActivity", foodTaste);
                        Random random = new Random();
                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot snapshot : documents) {
                            if(snapshot.get("country") != null && snapshot.get("country").equals(foodCountry)) {
                                // Log.d("MenuResultActivity", snapshot.get("country").toString());
                                if (snapshot.get("categoryFood") != null && snapshot.get("categoryFood").equals(foodCategory)) {
                                    // Log.d("MenuResultActivity", snapshot.get("categoryFood").toString());
                                    if (snapshot.get("categoryFood").equals("분식")) {
                                        Food food = snapshot.toObject(Food.class);
                                        String id = snapshot.getId();
                                        food.setId(id);
                                        FryFoodList.add(food);

                                        int randomIndex = random.nextInt(FryFoodList.size());
                                        textView.setText(FryFoodList.get(randomIndex).getName());
                                        Log.d("MenuResultActivity", "FryFoodList");

                                    } else if (snapshot.get("taste") != null && snapshot.get("taste").equals(foodTaste)) {
                                        Log.d("MenuResultActivity", snapshot.get("taste").toString());
                                        Log.d("MenuResultActivity", snapshot.get("name").toString());

                                        Food food = snapshot.toObject(Food.class);
                                        String id = snapshot.getId();
                                        food.setId(id);
                                        PerfectFoodList.add(food);

                                        int randomIndex = random.nextInt(PerfectFoodList.size());
                                        textView.setText(PerfectFoodList.get(randomIndex).getName());
                                        Log.d("MenuResultActivity", "PerfectFoodList");
                                    }
                                } else {
                                    Food food = snapshot.toObject(Food.class);
                                    String id = snapshot.getId();
                                    food.setId(id);
                                    GoodFoodList.add(food);

                                    int randomIndex = random.nextInt(GoodFoodList.size());
                                    textView.setText(GoodFoodList.get(randomIndex).getName());
                                    Log.d("MenuResultActivity", "GoodFoodList");
                                }
                            } else {
                                Food food = snapshot.toObject(Food.class);
                                String id = snapshot.getId();
                                food.setId(id);
                                NiceFoodList.add(food);

                                int randomIndex = random.nextInt(NiceFoodList.size());
                                textView.setText(NiceFoodList.get(randomIndex).getName());
                                Log.d("MenuResultActivity", "NiceFoodList");
                            }
                            }

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
