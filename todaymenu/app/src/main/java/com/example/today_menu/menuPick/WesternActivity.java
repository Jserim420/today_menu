package com.example.today_menu.menuPick;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.R;
import com.example.today_menu.menuPick.taste.Beef;
import com.example.today_menu.menuPick.taste.Spicy;

public class WesternActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_activity_western);


        pref = getSharedPreferences("food", Activity.MODE_PRIVATE);
        editor = pref.edit();

        Button riceBtn = findViewById(R.id.rice_btn);
        Button noodleBtn = findViewById(R.id.noodle_btn);
        Button beefBtn = findViewById(R.id.beef_btn);
        Button seafoodBtn = findViewById(R.id.seafood_btn);
        Button soupBtn = findViewById(R.id.soup_btn);
        Button vegetableBtn = findViewById(R.id.vegetable_btn);
        Button breadBtn = findViewById(R.id.bread_btn);

        riceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "밥");
                editor.apply();

                Intent intent = new Intent(WesternActivity.this, WesFlavor.class);
                startActivity(intent);
            }
        });

        noodleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "면");
                editor.apply();

                Intent intent = new Intent(WesternActivity.this, Spicy.class);
                startActivity(intent);
            }
        });

        beefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "고기");
                editor.apply();

                Intent intent = new Intent(WesternActivity.this, Beef.class);
                startActivity(intent);
            }
        });

        seafoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "해산물");
                editor.apply();

                Intent intent = new Intent(WesternActivity.this, Spicy.class);
                startActivity(intent);
            }
        });

        soupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "국/탕");
                editor.apply();

                Intent intent = new Intent(WesternActivity.this, MenuResultActivity.class);

                intent.putExtra("IntentKind", "양식 국/탕");
                startActivity(intent);
            }
        });

        vegetableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "채소");
                editor.apply();

                Intent intent = new Intent(WesternActivity.this, MenuResultActivity.class);
                intent.putExtra("IntentKind", "양식 채소");
                startActivity(intent);
            }
        });

        breadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "빵");
                editor.apply();

                Intent intent = new Intent(WesternActivity.this, MenuResultActivity.class);
                intent.putExtra("IntentKind", "양식 빵");
                startActivity(intent);
            }
        });
    }
}

