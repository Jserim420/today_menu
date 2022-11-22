package com.example.today_menu.menuPick.korean;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.R;

public class kor_Beef extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_kor_beef);

        Button cowBtn = findViewById(R.id.cow_btn);

        cowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_Beef.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button pigBtn = findViewById(R.id.pig_btn);

        pigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_Beef.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button chickenBtn = findViewById(R.id.chicken_btn);

        chickenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_Beef.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
    }
}
