package com.example.today_menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.today_menu.menuPick.Food;
import com.example.today_menu.menuPick.MenuResultActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MenuResultPopup extends Activity {

    Random random = new Random();
    FoodHistory foodHistory = new FoodHistory();

    ArrayList<Food> FoodList = new ArrayList<>();
    ArrayList<Food> PriceFoodList = new ArrayList<>();

    int foodPrice, price;
    String foodCountry;
    String foodCategory;
    String foodTaste ;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore fireStore;

    static final String format_yyMMdd = "yy/MM/dd";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_menuresult);



        fireStore = FirebaseFirestore.getInstance();
        TextView resultTv = findViewById(R.id.menuresult_tv);
        TextView infoTv = findViewById(R.id.info_tv);
        TextView infoTv2 = findViewById(R.id.info2_tv);


        foodCountry = getIntent().getStringExtra("FoodCountry");
        foodCategory = getIntent().getStringExtra("FoodCategory");
        foodTaste = getIntent().getStringExtra("FoodTaste");
        foodPrice = Integer.parseInt(getIntent().getStringExtra("FoodPrice"));
        String intentKind = getIntent().getStringExtra("IntentKind");

        Log.d("MenuResultActivity", foodPrice + ", " + foodCountry + ", " + foodCategory + ", " + foodTaste);


        LinearLayout again = findViewById(R.id.again);
        LinearLayout select = findViewById(R.id.select);
        LinearLayout home = findViewById(R.id.home);

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intentKind.equals("random")) {
                    PriceRandomList(resultTv, infoTv, infoTv2);
                } else {
                    PriceFoodList(resultTv, infoTv, infoTv2);
                }
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String menuResult = resultTv.getText().toString();
                foodHistory = addHistory(menuResult, getCurrentDate_yyMMdd());
                addFireStore(foodHistory);
                Intent intent = new Intent(MenuResultPopup.this, MenuResultActivity.class);
                intent.putExtra("MenuResult", menuResult);
                mOnClose(view);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuResultPopup.this, MainActivity.class);
                startActivity(intent);
            }
        });


        if(foodPrice == 0) {
            if (intentKind != null && intentKind.equals("random")) {
                loadRandomList(resultTv);
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
                loadFryList(resultTv, foodCategory);
            } else {
                Log.d("FoodPrice", String.valueOf(foodPrice));
                loadFoodList(resultTv); }
        } else if (foodPrice > 0){
            if (intentKind != null && intentKind.equals("random")) {
                PriceRandomList(resultTv, infoTv, infoTv2);
            } else if (intentKind != null) {
                switch (intentKind) {
                    case "분식":
                        foodCountry = "한식";
                        foodCategory = "분식";
                        PriceFryList(resultTv, infoTv, foodCategory, infoTv2);
                        break;
                    case "일식 국/탕":
                        foodCountry = "일식";
                        foodCategory = "국/탕";
                        PriceFryList(resultTv, infoTv, foodCategory, infoTv2);
                        break;
                    case "중식 볶음":
                        foodCountry = "중식";
                        foodCategory = "볶음";
                        PriceFryList(resultTv, infoTv, foodCategory, infoTv2);
                        break;
                    case "양식 빵":
                        foodCountry = "양식";
                        foodCategory = "빵";
                        PriceFryList(resultTv, infoTv, foodCategory, infoTv2);
                        break;
                    case "양식 채소":
                        foodCountry = "양식";
                        foodCategory = "채소";
                        PriceFryList(resultTv, infoTv, foodCategory, infoTv2);
                        break;
                    case "양식 국/탕":
                        foodCountry = "양식";
                        foodCategory = "국/탕";
                        PriceFryList(resultTv, infoTv, foodCategory, infoTv2);
                        break;
                    case " ":
                    case "":
                        PriceFoodList(resultTv, infoTv, infoTv2);
                        break;
                    default:
                        break;
                }
            } else {
                Log.d("FoodPriceUP", String.valueOf(foodPrice));
                PriceFoodList(resultTv, infoTv, infoTv2);
            }
        }



    }

    // 금액을 신경쓰지 않을때 음식
    public void loadFoodList(TextView result) {
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
    public void PriceFoodList(TextView result, TextView info, TextView info2) {
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
                            info.setText("사용자님이 고르신 금액에 맞는 음식이 없습니다.");
                            info2.setText("사용자님의 취양에 맞는 음식을 추천해드립니다.");
                            loadFoodList(result);
                        } else {
                            int randomIndex = random.nextInt(PriceFoodList.size());
                            String menuName = PriceFoodList.get(randomIndex).getName();
                            result.setText(menuName);
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
    public void loadRandomList(TextView result) {
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
    public void PriceRandomList(TextView result, TextView info, TextView info2) {
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
                            Log.d("MenuResultActivity", "PriceRandomList : FoodList");
                        } else {
                            Log.d("MenuResultActivity", "PriceRandomList -> loadRandomList");
                            info.setText("사용자님이 고르신 금액에 맞는 음식이 없습니다.");
                            info2.setText("사용자님의 취양에 맞는 음식을 추천해드립니다.");
                            loadRandomList(result);
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
    public void PriceFryList(TextView result, TextView info, String foodCategory, TextView info2) {
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
                    info.setText("사용자님이 고르신 금액에 맞는 음식이 없습니다.");
                    info2.setText("사용자님의 취양에 맞는 음식을 추천해드립니다.");
                    loadFryList(result, foodCategory);
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
    public void loadFryList(TextView result, String foodCategory) {
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

    public void mOnClose(View v) {
        Intent intent = new Intent();
        Log.d("Result", "Complete Change Password Popup");
        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }


    public FoodHistory addHistory(String menu, String currentDate) {
        foodHistory = new FoodHistory();
        firebaseAuth = FirebaseAuth.getInstance();
        foodHistory.setUserId(firebaseAuth.getCurrentUser().getEmail());
        foodHistory.setFoodName(menu);
        foodHistory.setDate(currentDate);
        return foodHistory;
    }

    public static String getCurrentDate_yyMMdd() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat(format_yyMMdd, Locale.getDefault());
        return format.format(currentTime);
    }

    public void addFireStore(FoodHistory mFoodHistory) {
        fireStore.collection("History").add(mFoodHistory).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("addFireStore", "Success");
                setResult(RESULT_OK);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("addFireStore", e.getMessage());
            }
        });
    }
}
