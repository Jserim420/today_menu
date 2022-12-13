package com.example.today_menu.menuPick;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.R;

public class StartQuestionActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_activity_country);

        Button koreanBtn = findViewById(R.id.korean_btn);
        Button japaneseBtn = findViewById(R.id.japanese_btn);
        Button westernBtn = findViewById(R.id.western_btn);
        Button chineseBtn = findViewById(R.id.chinese_btn);
        Button asianBtn = findViewById(R.id.asian_btn);

        pref = getSharedPreferences("food", Activity.MODE_PRIVATE);
        editor = pref.edit();
       Log.d("Price", pref.getString("FoodPrice","0"));

        koreanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCountry", "한식");
                editor.apply();

                Intent intent = new Intent(StartQuestionActivity.this, KoreanActivity.class);
                startActivity(intent);

            }
        });

        japaneseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCountry", "일식");
                editor.apply();

                Intent intent = new Intent(StartQuestionActivity.this, JapaneseActivity.class);
                startActivity(intent);
            }
        });

        westernBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCountry", "양식");
                editor.apply();

                Intent intent = new Intent(StartQuestionActivity.this, WesternActivity.class);
                startActivity(intent);
            }
        });

        chineseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCountry", "중식");
                editor.apply();

                Intent intent = new Intent(StartQuestionActivity.this, ChineseActivity.class);
                startActivity(intent);
            }
        });

        asianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCountry", "아시안");
                editor.apply();

                Intent intent = new Intent(StartQuestionActivity.this, AsianActivity.class);
                startActivity(intent);
            }
        });


    }



}



