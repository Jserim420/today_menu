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
import com.example.today_menu.menuPick.taste.Beef;
import com.example.today_menu.menuPick.taste.Temp;

public class KoreanActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    String foodCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_activity_korean);

        pref = getSharedPreferences("food", Activity.MODE_PRIVATE);
        editor = pref.edit();

        Log.d("Price", pref.getString("FoodPrice","0"));

        Button riceBtn = findViewById(R.id.rice_btn);
        Button noodleBtn = findViewById(R.id.noodle_btn);
        Button beefBtn = findViewById(R.id.beef_btn);
        Button seafoodBtn = findViewById(R.id.seafood_btn);
        Button soupBtn = findViewById(R.id.soup_btn);
        Button fryBtn = findViewById(R.id.fry_btn);

        riceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "밥");
                editor.apply();

                Intent intent = new Intent(KoreanActivity.this, KorFlavor.class);
                startActivity(intent);
            }
        });

        noodleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "면");
                editor.apply();

                Intent intent = new Intent(KoreanActivity.this, Temp.class);
                startActivity(intent);
            }
        });

        beefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "고기");
                editor.apply();

                Intent intent = new Intent(KoreanActivity.this, Beef.class);
                startActivity(intent);
            }
        });

        seafoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "해산물");
                editor.apply();

                Intent intent = new Intent(KoreanActivity.this, KorFlavor.class);
                startActivity(intent);
            }
        });

        soupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "국/탕");
                editor.apply();

                Intent intent = new Intent(KoreanActivity.this, KorSoup.class);
                startActivity(intent);
            }
        });

        fryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodCategory", "분식");
                editor.apply();

                Intent intent = new Intent(KoreanActivity.this, MenuResultActivity.class);
                intent.putExtra("IntentKind", "분식");
                startActivity(intent);
            }
        });
    }
}
