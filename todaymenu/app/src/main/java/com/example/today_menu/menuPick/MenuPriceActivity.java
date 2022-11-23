package com.example.today_menu.menuPick;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.R;

public class MenuPriceActivity extends AppCompatActivity{

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_activity_price);

        Button price5Btn = findViewById(R.id.price5_btn);
        Button price10Btn = findViewById(R.id.price10_btn);
        Button price15Btn = findViewById(R.id.price15_btn);
        Button price20Btn = findViewById(R.id.price20_btn);
        Button price30Btn = findViewById(R.id.price_over30_btn);
        Button priceOver30Btn = findViewById(R.id.price_over30_btn);

        pref = getSharedPreferences("food", Activity.MODE_PRIVATE);
        editor = pref.edit();

        price5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("FoodPrice", 5000);
                editor.apply();

                Intent intent = new Intent(MenuPriceActivity.this, StartQuestionActivity.class);
                startActivity(intent);
            }
        });

        price10Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("FoodPrice", 10000);
                editor.apply();

                Intent intent = new Intent(MenuPriceActivity.this, StartQuestionActivity.class);
                startActivity(intent);
            }
        });

        price15Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("FoodPrice", 15000);
                editor.apply();

                Intent intent = new Intent(MenuPriceActivity.this, StartQuestionActivity.class);
                startActivity(intent);
            }
        });

        price20Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("FoodPrice", 20000);
                editor.apply();

                Intent intent = new Intent(MenuPriceActivity.this, StartQuestionActivity.class);
                startActivity(intent);
            }
        });

        price30Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("FoodPrice", 30000);
                editor.apply();

                Intent intent = new Intent(MenuPriceActivity.this, StartQuestionActivity.class);
                startActivity(intent);
            }
        });

        priceOver30Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("FoodPrice", 31000);
                editor.apply();

                Intent intent = new Intent(MenuPriceActivity.this, StartQuestionActivity.class);
                startActivity(intent);
            }
        });
    }
}
