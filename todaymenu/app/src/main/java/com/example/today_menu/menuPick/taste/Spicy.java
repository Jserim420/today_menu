package com.example.today_menu.menuPick.taste;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.R;
import com.example.today_menu.menuPick.MenuResultActivity;

public class Spicy extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_activity_spicy);

        pref = getSharedPreferences("food",Activity.MODE_PRIVATE);
        editor = pref.edit();
        
        Button spicyBtn = findViewById(R.id.spicy_btn);
        Button nonSpicyBtn = findViewById(R.id.nonspicy_btn);

        spicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "매운거");
                editor.apply();

                Intent intent = new Intent(Spicy.this, MenuResultActivity.class);
                setItem(intent);
                startActivity(intent);
            }
        });

        nonSpicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "안매운거");
                editor.apply();

                Intent intent = new Intent(Spicy.this, MenuResultActivity.class);
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
