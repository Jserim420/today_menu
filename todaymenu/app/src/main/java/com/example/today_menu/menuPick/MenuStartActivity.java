package com.example.today_menu.menuPick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.today_menu.R;

public class MenuStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_activity_main);

        Button menuBtn = findViewById(R.id.pcik_btn);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuStartActivity.this, StartQuestionActivity.class);
                startActivity(intent);
            }
        });
    }


}