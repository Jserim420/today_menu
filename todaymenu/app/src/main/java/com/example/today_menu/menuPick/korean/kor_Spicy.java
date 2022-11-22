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

public class kor_Spicy extends AppCompatActivity {

    SharedPreferences pref;          // 프리퍼런스
    SharedPreferences.Editor editor;

    String foodCountry;
    String foodCategory;

    Food food = new Food();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_kor_spicy);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        foodCountry = pref.getString("FoodCountry","없음");
        food.setCountry(foodCountry);
        foodCategory = pref.getString("FoodCategory", "없음");

        Button spicyBtn = findViewById(R.id.spicy_btn);

        spicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("kor_Spicy_country", food.getCountry());
                
                Intent intent = new Intent(kor_Spicy.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button nonspicyBtn = findViewById(R.id.nonspicy_btn);

        nonspicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_Spicy.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
    }
}
