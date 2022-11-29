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

import org.w3c.dom.Document;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuResultActivity extends AppCompatActivity {

    Random random = new Random();

    ArrayList<Food> PerfectFoodList = new ArrayList<>();
    ArrayList<Food> FryFoodList = new ArrayList<>();
    ArrayList<Food> RandomFoodList = new ArrayList<>();

    String foodCountry ;
    String foodCategory ;
    String foodTaste ;

    FirebaseFirestore fireStore=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuresult);

        fireStore = FirebaseFirestore.getInstance();
        TextView resultTv = findViewById(R.id.result_tv);
        TextView infoTv = findViewById(R.id.info_tv);

        foodCountry = getIntent().getStringExtra("FoodCountry");
        foodCategory = getIntent().getStringExtra("FoodCategory");
        foodTaste = getIntent().getStringExtra("FoodTaste");

        Button againBtn = findViewById(R.id.again_btn);
        String intentKind = getIntent().getStringExtra("IntentKind");

        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuResultActivity.this, StartQuestionActivity.class);
                startActivity(intent);
            }
        });

        if(intentKind !=null && intentKind.equals("random")) {
            loadRandomList(resultTv, infoTv);
        } else if(intentKind !=null && intentKind.equals("분식")){
            loadFryList(resultTv,infoTv);
        } else loadFoodList(resultTv, infoTv);

    }

    public void loadFoodList(TextView result, TextView info) {
        fireStore.collection("Food")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        PerfectFoodList.clear();

                        Log.d("MenuResultActivity", foodCountry);
                        Log.d("MenuResultActivity", foodCategory);
                        Log.d("MenuResultActivity", foodTaste);
                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot snapshot : documents) {
                            if(snapshot.get("country") != null && snapshot.get("country").equals(foodCountry)) {
                                // Log.d("MenuResultActivity", snapshot.get("country").toString());
                                if (snapshot.get("categoryFood") != null && snapshot.get("categoryFood").equals(foodCategory)) {
                                    // Log.d("MenuResultActivity", snapshot.get("categoryFood").toString());
                                    if (snapshot.get("taste") != null && snapshot.get("taste").equals(foodTaste)) {
                                        Log.d("MenuResultActivity", snapshot.get("taste").toString());
                                        Log.d("MenuResultActivity", snapshot.get("name").toString());

                                        Food food = snapshot.toObject(Food.class);
                                        String id = snapshot.getId();
                                        food.setId(id);
                                        PerfectFoodList.add(food);
                                    }
                                }
                            }
                        }
                        int randomIndex = random.nextInt(PerfectFoodList.size());
                        String menuName = PerfectFoodList.get(randomIndex).getName();
                        result.setText(menuName);
                        info.setText("오늘은 맛있는 " + menuName + "을 먹으세요.");
                        Log.d("MenuResultActivity", "PerfectFoodList");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MenuResultActivity", e.getMessage());
                    }
                });

    }

    public void loadRandomList(TextView result, TextView info) {
        fireStore.collection("Food")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        RandomFoodList.clear();

                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot snapshot : documents) {
                            String string = snapshot.get("price").toString();
                            Food food = snapshot.toObject(Food.class);
                            String id = snapshot.getId();
                            food.setId(id);
                            RandomFoodList.add(food);
                        }
                        int randomIndex = random.nextInt(RandomFoodList.size());
                        String menuName = RandomFoodList.get(randomIndex).getName();
                        result.setText(menuName);
                        info.setText("오늘은 맛있는 " + menuName + "을 먹으세요.");
                        Log.d("MenuResultActivity", "RandomFoodList");
                    }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("MenuResultActivity", e.getMessage());
            }
        });
    }

    public void loadFryList(TextView result, TextView info) {
        fireStore.collection("Food").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot: queryDocumentSnapshots) {
                    if (snapshot.get("categoryFood") != null && snapshot.get("categoryFood").equals("분식")) {
                        Food food = snapshot.toObject(Food.class);
                        String id = snapshot.getId();
                        food.setId(id);
                        FryFoodList.add(food);
                        }
                    }
                int randomIndex = random.nextInt(FryFoodList.size());
                String menuName = FryFoodList.get(randomIndex).getName();
                result.setText(menuName);
                info.setText("오늘은 맛있는 " + menuName + "을 먹으세요.");
                Log.d("MenuResultActivity", "FryFoodList");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("MenuResultActivity", "FryFoodList");
            }
        });
    }

}
