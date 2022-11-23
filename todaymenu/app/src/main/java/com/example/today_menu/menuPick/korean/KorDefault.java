package com.example.today_menu.menuPick.korean;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.R;
import com.example.today_menu.menuPick.MenuResultActivity;

public class KorDefault extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_kor_flavor);

        Button spicyBtn = findViewById(R.id.spicy_btn);
        Button nonSpicyBtn = findViewById(R.id.nonspicy_btn);
        Button cleanBtn = findViewById(R.id.clean_btn);
        Button saltyBtn = findViewById(R.id.salty_btn);

        pref = getSharedPreferences("food", Activity.MODE_PRIVATE);
        editor = pref.edit();


        spicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "매운거");
                editor.apply();

                Intent intent = new Intent(KorDefault.this, MenuResultActivity.class);
                getItem(intent);
                startActivity(intent);
            }
        });

        nonSpicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "안매운거");
                editor.apply();

                Intent intent = new Intent(KorDefault.this, MenuResultActivity.class);
                getItem(intent);
                startActivity(intent);
            }
        });

        cleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "시원한거");
                editor.apply();

                Intent intent = new Intent(KorDefault.this, MenuResultActivity.class);
                getItem(intent);
                startActivity(intent);
            }
        });

        saltyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "짠거");
                editor.apply();

                Intent intent = new Intent(KorDefault.this, MenuResultActivity.class);
                getItem(intent);
                startActivity(intent);
            }
        });

    }

    public void getItem(Intent intent) {
        intent.putExtra("FoodCountry", pref.getString("FoodCountry","없음"));
        intent.putExtra("FoodTaste", pref.getString("FoodTaste","없음"));
        intent.putExtra("FoodCategory", pref.getString("FoodCategory","없음"));
    }

}
