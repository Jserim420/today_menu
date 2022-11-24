package com.example.today_menu.menuPick.japanese;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.R;
import com.example.today_menu.menuPick.korean.KoreanActivity;


public class jap_Spicy extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_jap_spicy);

        Button spicyBtn = findViewById(R.id.spicy_btn);

        spicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jap_Spicy.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button nonspicyBtn = findViewById(R.id.nonspicy_btn);

        nonspicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jap_Spicy.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
    }
}