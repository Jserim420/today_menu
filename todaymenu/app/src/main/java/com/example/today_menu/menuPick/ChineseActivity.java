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
import com.example.today_menu.menuPick.taste.Spicy;
import com.example.today_menu.menuPick.taste.Temp;

public class ChineseActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_activity_chinese);

        pref = getSharedPreferences("food", Activity.MODE_PRIVATE);
        editor = pref.edit();

        Button riceBtn = findViewById(R.id.rice_btn);
        Button noodleBtn = findViewById(R.id.noodle_btn);
        Button beefBtn = findViewById(R.id.beef_btn);
        Button seafoodBtn = findViewById(R.id.seafood_btn);
        Button soupBtn = findViewById(R.id.soup_btn);
        Button fryBtn = findViewById(R.id.fry_btn);

        Log.d("Price", pref.getString("FoodPrice","0"));


        riceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChineseActivity.this, ChineseFlavor.class);
                editor.putString("FoodCategory", riceBtn.getText().toString());
                editor.apply();
                startActivity(intent);
            }
        });


        noodleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChineseActivity.this, Temp.class);
                editor.putString("FoodCategory", noodleBtn.getText().toString());
                editor.apply();
                startActivity(intent);
            }
        });


        beefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChineseActivity.this, ChineseBeef.class);
                editor.putString("FoodCategory", beefBtn.getText().toString());
                editor.apply();
                startActivity(intent);
            }
        });


        seafoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChineseActivity.this, ChineseFlavor.class);
                editor.putString("FoodCategory", seafoodBtn.getText().toString());
                editor.apply();
                startActivity(intent);
            }
        });


        soupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChineseActivity.this, Spicy.class);
                editor.putString("FoodCategory", soupBtn.getText().toString());
                editor.apply();
                startActivity(intent);
            }
        });

        fryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChineseActivity.this, MenuResultActivity.class);
                editor.putString("FoodCategory", fryBtn.getText().toString());
                editor.apply();

                intent.putExtra("IntentKind", "중식 볶음");
                startActivity(intent);
            }
        });
    }
}
