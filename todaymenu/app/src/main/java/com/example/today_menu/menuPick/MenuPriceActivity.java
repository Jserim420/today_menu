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
        Button price30Btn = findViewById(R.id.price30_btn);
        Button noMatterBtn = findViewById(R.id.nomatter_btn);

        pref = getSharedPreferences("food", Activity.MODE_PRIVATE);
        editor = pref.edit();

        String intentKind = getIntent().getStringExtra("IntentKind");

        price5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntent("5000", intentKind);
            }
        });

        price10Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntent("10000", intentKind);
            }
        });

        price15Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntent("15000", intentKind);
            }
        });

        price20Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntent("20000", intentKind);
            }
        });

        price30Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntent("30000", intentKind);
            }
        });


        noMatterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntent("0", intentKind);
            }
        });
    }

    public void setIntent(String price, String intentKind) {

        if(intentKind.equals("random")) {
            Intent intent = new Intent(MenuPriceActivity.this, MenuResultActivity.class);
            intent.putExtra("IntentKind", "random");
            editor.putString("FoodPrice", price);
            editor.apply();
            intent.putExtra("FoodPrice", pref.getString("FoodPrice", "0"));
            startActivity(intent);
        }else {
            Intent intent = new Intent(MenuPriceActivity.this, StartQuestionActivity.class);
            editor.putString("FoodPrice", price);
            editor.apply();
            startActivity(intent);
        }
    }

}
