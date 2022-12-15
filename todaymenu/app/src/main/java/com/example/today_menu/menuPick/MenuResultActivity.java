package com.example.today_menu.menuPick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.today_menu.MainActivity;
import com.example.today_menu.R;
import com.example.today_menu.mapdata.MenuMapActivity;

public class MenuResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuresult);

        TextView menuTv = findViewById(R.id.menu_tv);
        TextView infoTv = findViewById(R.id.info_tv);

        Button restaurantBtn = findViewById(R.id.restaurant_btn);
        Button homeBtn = findViewById(R.id.home_btn);

        String resultMenu = getIntent().getStringExtra("MenuResult");

        menuTv.setText(resultMenu);
        infoTv.setText("오늘은 맛있는 " + resultMenu + " (을)를 드세요.");

        restaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuResultActivity.this, MenuMapActivity.class);
                intent.putExtra("MenuResult", resultMenu);

                startActivity(intent);

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
