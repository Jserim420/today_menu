package com.example.today_menu.menuPick.japanese;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.R;
import com.example.today_menu.menuPick.korean.KoreanActivity;

public class jap_Beef extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_jap_beef);

        Button cowBtn = findViewById(R.id.cow_btn);

        cowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jap_Beef.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button pigBtn = findViewById(R.id.pig_btn);

        pigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jap_Beef.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button chickenBtn = findViewById(R.id.sheep_btn);

        chickenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jap_Beef.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
    }
}
