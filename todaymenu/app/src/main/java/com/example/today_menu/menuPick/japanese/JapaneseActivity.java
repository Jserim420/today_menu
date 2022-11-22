package com.example.today_menu.menuPick.japanese;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.R;

public class JapaneseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_japanese_main);

        Button riceBtn = findViewById(R.id.rice_btn);

        riceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JapaneseActivity.this, jap_Spicy.class);
                startActivity(intent);
            }
        });

        Button noodleBtn = findViewById(R.id.noodle_btn);

        noodleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JapaneseActivity.this, jap_Temp.class);
                startActivity(intent);
            }
        });

        Button beefBtn = findViewById(R.id.beef_btn);

        beefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JapaneseActivity.this, jap_Beef.class);
                startActivity(intent);
            }
        });

        Button seafoodBtn = findViewById(R.id.seafood_btn);

        seafoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JapaneseActivity.this, JapaneseActivity.class);
                startActivity(intent);
            }
        });

        Button soupBtn = findViewById(R.id.soup_btn);

        soupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JapaneseActivity.this, JapaneseActivity.class);
                startActivity(intent);
            }
        });
    }
}
