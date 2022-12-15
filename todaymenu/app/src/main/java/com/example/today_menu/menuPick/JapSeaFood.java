package com.example.today_menu.menuPick;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.MenuResultPopup;
import com.example.today_menu.R;

public class JapSeaFood extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_activity_flavor);

        Button nonSpicyBtn = findViewById(R.id.spicy_btn);
        nonSpicyBtn.setText("안매운 맛");
        Button coldBtn = findViewById(R.id.nonspicy_btn);
        coldBtn.setText("차가운 음식");
        Button hotBtn = findViewById(R.id.clean_btn);
        hotBtn.setText("따뜻한 음식");
        findViewById(R.id.salty_btn).setVisibility(View.GONE);

        pref = getSharedPreferences("food", Activity.MODE_PRIVATE);
        editor = pref.edit();

        Log.d("Price", pref.getString("FoodPrice","0"));
        nonSpicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "안매운거");
                editor.apply();

                Intent intent = new Intent(JapSeaFood.this, MenuResultPopup.class);
                setItem(intent);
                startActivity(intent);
            }
        });

        coldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "차가운거");
                editor.apply();

                Intent intent = new Intent(JapSeaFood.this, MenuResultPopup.class);
                setItem(intent);
                startActivity(intent);
            }
        });

        hotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "따뜻한거");
                editor.apply();

                Intent intent = new Intent(JapSeaFood.this, MenuResultPopup.class);
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
