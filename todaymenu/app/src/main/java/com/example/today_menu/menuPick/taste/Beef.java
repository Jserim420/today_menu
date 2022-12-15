package com.example.today_menu.menuPick.taste;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.MenuResultPopup;
import com.example.today_menu.R;

public class Beef extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_activity_beef);

        Button cowBtn = findViewById(R.id.cow_btn);
        Button pigBtn = findViewById(R.id.pig_btn);
        Button chickenBtn = findViewById(R.id.chicken_btn);
        Button sheepBtn = findViewById(R.id.sheep_btn);
        sheepBtn.setVisibility(View.GONE);

        findViewById(R.id.sheep_btn).setVisibility(View.GONE);

        pref = getSharedPreferences("food", Activity.MODE_PRIVATE);
        editor = pref.edit();

        cowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste","소");
                editor.apply();

                Intent intent = new Intent(Beef.this, MenuResultPopup.class);
                setItem(intent);
                startActivity(intent);
            }
        });

        pigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste","돼지");
                editor.apply();

                Intent intent = new Intent(Beef.this, MenuResultPopup.class);
                setItem(intent);
                startActivity(intent);
            }
        });


        chickenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste","닭");
                editor.apply();

                Intent intent = new Intent(Beef.this, MenuResultPopup.class);
                setItem(intent);
                startActivity(intent);
            }
        });
    }

    public void setItem(Intent intent) {
        intent.putExtra("FoodPrice", pref.getString("FoodPrice","0"));
        intent.putExtra("FoodCountry", pref.getString("FoodCountry","없음"));
        intent.putExtra("FoodTaste", pref.getString("FoodTaste","없음"));
        intent.putExtra("FoodCategory", pref.getString("FoodCategory","없음"));
        intent.putExtra("IntentKind", " ");

    }


}
