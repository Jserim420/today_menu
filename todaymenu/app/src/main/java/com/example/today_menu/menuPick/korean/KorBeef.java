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

public class KorBeef extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_kor_beef);

        Button cowBtn = findViewById(R.id.cow_btn);
        Button pigBtn = findViewById(R.id.pig_btn);
        Button chickenBtn = findViewById(R.id.chicken_btn);

        pref = getSharedPreferences("food", Activity.MODE_PRIVATE);
        editor = pref.edit();

        cowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste","소");
                editor.apply();

                Intent intent = new Intent(KorBeef.this, MenuResultActivity.class);
                getItem(intent);
                startActivity(intent);
            }
        });

        pigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste","돼지");
                editor.apply();

                Intent intent = new Intent(KorBeef.this, MenuResultActivity.class);
                getItem(intent);
                startActivity(intent);
            }
        });


        chickenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste","닭");
                editor.apply();

                Intent intent = new Intent(KorBeef.this, MenuResultActivity.class);
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
