package com.example.today_menu.menuPick;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.today_menu.R;

public class MenuStartActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    String foodCountry;
    String foodCategory;
    String foodTaste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_activity_main);

        Button menuBtn = findViewById(R.id.pcik_btn);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuStartActivity.this, StartQuestionActivity.class);
                startActivity(intent);
            }
        });
    }


}