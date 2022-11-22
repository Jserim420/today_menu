package com.example.today_menu.menuPick;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.Food;
import com.example.today_menu.R;
import com.example.today_menu.menuPick.japanese.JapaneseActivity;
import com.example.today_menu.menuPick.korean.KoreanActivity;

public class StartQuestionActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_mainq_main);

        Button koreanBtn = findViewById(R.id.korean_btn);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        getSharedPreferences("pref", Activity.MODE_PRIVATE);

        koreanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCountry", "한식");
                editor.apply();

                Intent intent = new Intent(StartQuestionActivity.this, KoreanActivity.class);
                startActivity(intent);

            }
        });



        Button JapaneseBtn = findViewById(R.id.japanese_btn);

        JapaneseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartQuestionActivity.this, JapaneseActivity.class);
                startActivity(intent);
            }
        });

    }



}



