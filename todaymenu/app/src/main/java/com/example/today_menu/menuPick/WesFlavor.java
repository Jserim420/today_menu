package com.example.today_menu.menuPick;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.R;

public class WesFlavor extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_activity_flavor);

        Button spicyBtn = findViewById(R.id.spicy_btn);
        Button nonSpicyBtn = findViewById(R.id.nonspicy_btn);
        Button saltyBtn = findViewById(R.id.clean_btn);
        saltyBtn.setText("느끼한 맛");
        findViewById(R.id.salty_btn).setVisibility(View.GONE);

        pref = getSharedPreferences("food", Activity.MODE_PRIVATE);
        editor = pref.edit();


        spicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "매운거");
                editor.apply();

                Intent intent = new Intent(WesFlavor.this, MenuResultActivity.class);
                setItem(intent);
                startActivity(intent);
            }
        });

        nonSpicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "안매운거");
                editor.apply();

                Intent intent = new Intent(WesFlavor.this, MenuResultActivity.class);
                setItem(intent);
                startActivity(intent);
            }
        });

        saltyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("FoodTaste", "느끼한거");
                editor.apply();

                Intent intent = new Intent(WesFlavor.this, MenuResultActivity.class);
                setItem(intent);
                startActivity(intent);
            }
        });

    }

    public void setItem(Intent intent) {
        intent.putExtra("FoodPrice", pref.getString("FoodPrice","없음"));
        intent.putExtra("FoodCountry", pref.getString("FoodCountry","없음"));
        intent.putExtra("FoodTaste", pref.getString("FoodTaste","없음"));
        intent.putExtra("FoodCategory", pref.getString("FoodCategory","없음"));
        intent.putExtra("IntentKind", " ");
    }
    
}
