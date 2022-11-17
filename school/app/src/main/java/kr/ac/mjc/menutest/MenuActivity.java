package kr.ac.mjc.menutest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.mjc.menutest.japanese.JapaneseActivity;
import kr.ac.mjc.menutest.korean.KoreanActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainq_main);

        Button koreanBtn = findViewById(R.id.korean_btn);

        koreanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button JapaneseBtn = findViewById(R.id.japanese_btn);

        JapaneseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, JapaneseActivity.class);
                startActivity(intent);
            }
        });

    }



}



