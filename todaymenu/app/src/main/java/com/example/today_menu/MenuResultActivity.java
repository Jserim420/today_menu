package com.example.today_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.menuPick.MenuStartActivity;

import java.util.Random;

public class MenuResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuresult);

        TextView menuResult = findViewById(R.id.result_tv);
        String[] randomResult = getResources().getStringArray(R.array.krs);
        Random random = new Random();
        int n = random.nextInt(randomResult.length - 1);

        menuResult.setText(randomResult[n]);

        Button restaurantBtn = findViewById(R.id.restaurant_btn);
        Button againBtn = findViewById(R.id.again_btn);

        restaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuResultActivity.this, MenuStartActivity.class);
            }
        });
    }
}
