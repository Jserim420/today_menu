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

public class KoreanActivity extends AppCompatActivity {

    SharedPreferences pref;          // 프리퍼런스
    SharedPreferences.Editor editor;

    String foodCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_korean_main);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        foodCountry = pref.getString("FoodCountry","없음");

        Button riceBtn = findViewById(R.id.rice_btn);
        Button noodleBtn = findViewById(R.id.noodle_btn);
        Button beefBtn = findViewById(R.id.beef_btn);
        Button seafoodBtn = findViewById(R.id.seafood_btn);
        Button soupBtn = findViewById(R.id.soup_btn);

        riceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "밥");
                editor.apply();

                Intent intent = new Intent(KoreanActivity.this, kor_Spicy.class);
                startActivity(intent);
            }
        });

        noodleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KoreanActivity.this, kor_Temp.class);
                startActivity(intent);
            }
        });

        beefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KoreanActivity.this, kor_Beef.class);
                startActivity(intent);
            }
        });

        seafoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KoreanActivity.this, kor_Flavor.class);
                startActivity(intent);
            }
        });

        soupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KoreanActivity.this, kor_Flavor.class);
                startActivity(intent);
            }
        });
    }
}
