package com.example.today_menu.menuPick.korean;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.Food;
import com.example.today_menu.R;
import com.example.today_menu.menuPick.MenuResultActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class kor_Spicy extends AppCompatActivity {

    SharedPreferences pref;          // 프리퍼런스
    SharedPreferences.Editor editor;

    String foodCountry;
    String foodCategory;
    String foodTaste;

    Food food = new Food();

    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_kor_spicy);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        foodCountry = pref.getString("FoodCountry","없음");
        food.setCountry(foodCountry);
        foodCategory = pref.getString("FoodCategory", "없음");
        food.setCategoryFood(foodCategory);

        Button spicyBtn = findViewById(R.id.spicy_btn);
        Button nonSpicyBtn = findViewById(R.id.nonspicy_btn);

        spicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "매운거");
                editor.apply();
                foodTaste = pref.getString("FoodTaste", "없음");
                food.setCategoryFood(foodTaste);

                Intent intent = new Intent(kor_Spicy.this, MenuResultActivity.class);

                intent.putExtra("country",foodCountry);
                intent.putExtra("category", foodCategory);
                intent.putExtra("taste", foodTaste);

                startActivity(intent);
            }
        });


        nonSpicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_Spicy.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
    }
}
