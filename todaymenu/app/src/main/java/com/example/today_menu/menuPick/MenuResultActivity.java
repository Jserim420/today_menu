package com.example.today_menu.menuPick;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.MainActivity;
import com.example.today_menu.R;
import com.example.today_menu.mapdata.MenuMapActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuResultActivity extends AppCompatActivity {

    Random random = new Random();

//    ArrayList<Food> PerfectFoodList = new ArrayList<>();
    ArrayList<Food> FoodList = new ArrayList<>();
//    ArrayList<Food> FryFoodList = new ArrayList<>();
//    ArrayList<Food> RandomFoodList = new ArrayList<>();
    ArrayList<Food> PriceFoodList = new ArrayList<>();

    int foodPrice, price;
    String foodCountry;
    String foodCategory;
    String foodTaste ;

    FirebaseFirestore fireStore=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuresult);

        fireStore = FirebaseFirestore.getInstance();
        TextView resultTv = findViewById(R.id.result_tv);
        TextView infoTv = findViewById(R.id.info_tv);
        TextView info2Tv = findViewById(R.id.other_info_tv);
        TextView info3Tv = findViewById(R.id.another_info_tv);


        foodCountry = getIntent().getStringExtra("FoodCountry");
        foodCategory = getIntent().getStringExtra("FoodCategory");
        foodTaste = getIntent().getStringExtra("FoodTaste");
        foodPrice = Integer.parseInt(getIntent().getStringExtra("FoodPrice"));
        String intentKind = getIntent().getStringExtra("IntentKind");

        Log.d("MenuResultActivity", foodPrice + ", " + foodCountry + ", " + foodCategory + ", " + foodTaste);


        Button againBtn = findViewById(R.id.again_btn);
        Button homeBtn = findViewById(R.id.home_btn);
        Button restaurantBtn = findViewById(R.id.restaurant_btn);

        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intentKind.equals("random")) {
                    PriceRandomList(resultTv, infoTv, info2Tv, info3Tv);
                } else {
                    PriceFoodList(resultTv, infoTv, info2Tv, info3Tv);
                }
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        restaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuResultActivity.this, MenuMapActivity.class);
                intent.putExtra("MenuResult", resultTv.getText().toString());

                startActivity(intent);

            }
        });



        if(foodPrice == 0) {
            info2Tv.setVisibility(View.GONE);
            info3Tv.setVisibility(View.GONE);
            if (intentKind != null && intentKind.equals("random")) {
                loadRandomList(resultTv, infoTv);
            } else if (intentKind != null) {
                switch (intentKind) {
                    case "분식":
                        foodCountry = "한식";
                        foodCategory = "분식";
                        break;
                    case "일식 국/탕":
                        foodCountry = "일식";
                        foodCategory = "국/탕";
                        break;
                    case "중식 볶음":
                        foodCountry = "중식";
                        foodCategory = "볶음";
                        break;
                    case "양식 빵":
                        foodCountry = "양식";
                        foodCategory = "빵";
                        break;
                    case "양식 채소":
                        foodCountry = "양식";
                        foodCategory = "채소";
                        break;
                    case "양식 국/탕":
                        foodCountry = "양식";
                        foodCategory = "국/탕";
                        break;
                    default:
                        break;
                }
                Log.d("intentKind", intentKind);
                loadFryList(resultTv, infoTv, foodCategory);
            } else {
                Log.d("FoodPrice", String.valueOf(foodPrice));
                loadFoodList(resultTv, infoTv); }
        } else if (foodPrice > 0){
            if (intentKind != null && intentKind.equals("random")) {
                PriceRandomList(resultTv, infoTv, info2Tv, info3Tv);
            } else if (intentKind != null) {
                switch (intentKind) {
                    case "분식":
                        foodCountry = "한식";
                        foodCategory = "분식";
                        PriceFryList(resultTv, infoTv, foodCategory, info2Tv, info3Tv);
                        break;
                    case "일식 국/탕":
                        foodCountry = "일식";
                        foodCategory = "국/탕";
                        PriceFryList(resultTv, infoTv, foodCategory, info2Tv, info3Tv);
                        break;
                    case "중식 볶음":
                        foodCountry = "중식";
                        foodCategory = "볶음";
                        PriceFryList(resultTv, infoTv, foodCategory, info2Tv, info3Tv);
                        break;
                    case "양식 빵":
                        foodCountry = "양식";
                        foodCategory = "빵";
                        PriceFryList(resultTv, infoTv, foodCategory, info2Tv, info3Tv);
                        break;
                    case "양식 채소":
                        foodCountry = "양식";
                        foodCategory = "채소";
                        PriceFryList(resultTv, infoTv, foodCategory, info2Tv, info3Tv);
                        break;
                    case "양식 국/탕":
                        foodCountry = "양식";
                        foodCategory = "국/탕";
                        PriceFryList(resultTv, infoTv, foodCategory, info2Tv, info3Tv);
                        break;
                    case " ":
                    case "":
                        PriceFoodList(resultTv, infoTv, info2Tv, info3Tv);
                        break;
                    default:
                        break;
                }
            } else {
                Log.d("FoodPriceUP", String.valueOf(foodPrice));
                PriceFoodList(resultTv, infoTv, info2Tv, info3Tv);
            }
        }



    }

    // 금액을 신경쓰지 않을때 음식
    public void loadFoodList(TextView result, TextView info) {
        fireStore.collection("Food")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        FoodList.clear();

                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot snapshot : documents) {
                            if (snapshot.get("country") != null && snapshot.get("country").equals(foodCountry)) {
                                // Log.d("MenuResultActivity", snapshot.get("country").toString());
                                if (snapshot.get("categoryFood") != null && snapshot.get("categoryFood").equals(foodCategory)) {
                                    // Log.d("MenuResultActivity", snapshot.get("categoryFood").toString());
                                    if (snapshot.get("taste") != null && snapshot.get("taste").equals(foodTaste)) {
                                        Log.d("MenuResultActivity", snapshot.get("taste").toString());
                                        Log.d("MenuResultActivity", snapshot.get("name").toString());
                                        Log.d("MenuResultActivity", snapshot.get("price").toString());

                                        Food food = snapshot.toObject(Food.class);
                                        String id = snapshot.getId();
                                        food.setId(id);
                                        FoodList.add(food);
                                    }
                                }
                            }
                        }
                                if(FoodList.size()>0) {
                                    int randomIndex = random.nextInt(FoodList.size());
                                    String menuName = FoodList.get(randomIndex).getName();
                                    result.setText(menuName);
                                    info.setText("오늘은 맛있는 " + menuName + "을(를) 드세요.");
                                    Log.d("MenuResultActivity", "loadFoodList : FoodList");
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

    // 금액을 정하고 음식을 전달할때
    public void PriceFoodList(TextView result, TextView info, TextView info2, TextView info3) {
        fireStore.collection("Food")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        FoodList.clear();
                        PriceFoodList.clear();

                        Log.d("MenuResultActivity", foodCountry);
                        Log.d("MenuResultActivity", foodCategory);
                        Log.d("MenuResultActivity", foodTaste);
                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot snapshot : documents) {
                            price = Integer.valueOf(String.valueOf(snapshot.get("price")));
                            if (snapshot.get("price") != null && price <= foodPrice) {
                                Log.d("price", String.valueOf(price));
                                if (snapshot.get("country") != null && snapshot.get("country").equals(foodCountry)) {
                                    // Log.d("MenuResultActivity", snapshot.get("country").toString());
                                    if (snapshot.get("categoryFood") != null && snapshot.get("categoryFood").equals(foodCategory)) {
                                        // Log.d("MenuResultActivity", snapshot.get("categoryFood").toString());
                                        if (snapshot.get("taste") != null && snapshot.get("taste").equals(foodTaste)) {
                                            Log.d("MenuResultActivity", snapshot.get("taste").toString());
                                            Log.d("MenuResultActivity", snapshot.get("name").toString());
                                            Log.d("MenuResultActivity", snapshot.get("price").toString());

                                            Food food = snapshot.toObject(Food.class);
                                            String id = snapshot.getId();
                                            food.setId(id);
                                            PriceFoodList.add(food);
                                        }
                                    }
                                }
                            }
                        }

                            if (PriceFoodList.size() < 1) {
                                Log.d("MenuResultActivity", "PriceFoodList -> loadFoodList");
                                loadFoodList(result, info);
                                info2.setText("사용자님이 선택하신 금액에 맞는 음식이 없습니다.");
                                info3.setText("대신 사용자님의 취향에 맞는 음식을 추천해 드릴게요.");
                            } else {
                                int randomIndex = random.nextInt(PriceFoodList.size());
                                String menuName = PriceFoodList.get(randomIndex).getName();
                                result.setText(menuName);
                                info.setText("오늘은 맛있는 " + menuName + "을 먹으세요.");
                                Log.d("MenuResultActivity", "PriceFoodList : PriceFoodList");
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

    // 사용자가 금액을 선택하지 않았을 때 랜덤음식추천
    public void loadRandomList(TextView result, TextView info) {
        fireStore.collection("Food")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        FoodList.clear();

                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot : documents) {
                            Food food = snapshot.toObject(Food.class);
                            String id = snapshot.getId();
                            food.setId(id);
                            FoodList.add(food);
                        }


                            if (FoodList.size() > 0) {
                                int randomIndex = random.nextInt(FoodList.size());
                                String menuName = FoodList.get(randomIndex).getName();
                                result.setText(menuName);
                                info.setText("오늘은 맛있는 " + menuName + "을(를) 드세요.");
                                Log.d("MenuResultActivity", "loadFandomList : FoodList");
                            }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MenuResultActivity", e.getMessage());
                    }
                });

    }

    //사용자가 금액을 선택했을 때 음식 추천
    public void PriceRandomList(TextView result, TextView info, TextView info2, TextView info3) {
        fireStore.collection("Food")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        FoodList.clear();

                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot : documents) {
                            price = Integer.valueOf(String.valueOf(snapshot.get("price")));
                            if (snapshot.get("price") != null && price <= foodPrice) {
//                                Log.d("MenuResultActivity", snapshot.get("taste").toString());
//                                Log.d("MenuResultActivity", snapshot.get("name").toString());
//                                Log.d("MenuResultActivity", snapshot.get("price").toString());
                                Food food = snapshot.toObject(Food.class);
                                String id = snapshot.getId();
                                food.setId(id);
                                PriceFoodList.add(food);
                            }
                        }

                        if (PriceFoodList.size() > 0) {
                                int randomIndex = random.nextInt(PriceFoodList.size());
                                String menuName = PriceFoodList.get(randomIndex).getName();
                                result.setText(menuName);
                                info.setText("오늘은 맛있는 " + menuName + "을 먹으세요.");
                                Log.d("MenuResultActivity", "PriceRandomList : FoodList");
                            } else {
                                Log.d("MenuResultActivity", "PriceRandomList -> loadRandomList");
                                loadRandomList(result, info);
                                info2.setText("사용자님이 선택하신 금액에 맞는 음식이 없습니다.");
                                info3.setText("대신 사용자님의 취향에 맞는 음식을 추천해 드릴게요.");
                            }
                        }



                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MenuResultActivity", "PriceRandomList : " + e.getMessage());
                    }
                });

    }

    // 가격을 정하고 맛을 정하지 않는 음식 추천
    public void PriceFryList(TextView result, TextView info, String foodCategory, TextView info2, TextView info3) {
        fireStore.collection("Food").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                FoodList.clear();
                PriceFoodList.clear();

                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot: queryDocumentSnapshots) {
                    price = Integer.valueOf(String.valueOf(snapshot.get("price")));
                    if (snapshot.get("country") != null && price <= foodPrice) {
                        if (snapshot.get("country") != null && snapshot.get("country").equals(foodCountry)) {
                            if (snapshot.get("categoryFood") != null && snapshot.get("categoryFood").equals(foodCategory)) {
                                Log.d("MenuResultActivity", snapshot.get("taste").toString());
                                Log.d("MenuResultActivity", snapshot.get("name").toString());
                                Log.d("MenuResultActivity", snapshot.get("price").toString());
                                Food food = snapshot.toObject(Food.class);
                                String id = snapshot.getId();
                                food.setId(id);
                                PriceFoodList.add(food);
                            }
                        }
                    }
                }


                    if (PriceFoodList.size() > 0) {
                        int randomIndex = random.nextInt(PriceFoodList.size());
                        String menuName = PriceFoodList.get(randomIndex).getName();
                        result.setText(menuName);
                        info.setText("오늘은 맛있는 " + menuName + "을 먹으세요.");
                       Log.d("MenuResultActivity", "PriceFryList : FoodList");
                    } else if (PriceFoodList.size() < 1) {
                        Log.d("MenuResultActivity", "PriceFryList -> loadFryList");
                        loadFryList(result, info, foodCategory);
                        info2.setText("사용자님이 선택하신 금액에 맞는 음식이 없습니다.");
                        info3.setText("대신 사용자님의 취향에 맞는 음식을 추천해 드릴게요.");
                    }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("MenuResultActivity", "PriceFryList" + e.getMessage());
            }
        });
    }

    // 가격을 정하지 않고 맛을 정하지 않는 음식 추천
    public void loadFryList(TextView result, TextView info, String foodCategory) {
        fireStore.collection("Food").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                    if (snapshot.get("country") != null && snapshot.get("country").equals(foodCountry)) {
                        if (snapshot.get("categoryFood") != null && snapshot.get("categoryFood").equals(foodCategory)) {
                            Log.d("MenuResultActivity", snapshot.get("taste").toString());
                            Log.d("MenuResultActivity", snapshot.get("name").toString());
                            Log.d("MenuResultActivity", snapshot.get("price").toString());
                            Food food = snapshot.toObject(Food.class);
                            String id = snapshot.getId();
                            food.setId(id);
                            FoodList.add(food);
                        }
                    }
                }
                    if(FoodList.size()>0) {
                        int randomIndex = random.nextInt(FoodList.size());
                        String menuName = FoodList.get(randomIndex).getName();
                        result.setText(menuName);
                        info.setText("오늘은 맛있는 " + menuName + "을(를) 드세요.");
                        Log.d("MenuResultActivity", "FryFoodList : FoodList");
                    }
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("MenuResultActivity", "FryFoodList" + e.getMessage());
            }
        });
    }

}
