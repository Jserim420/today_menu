package kr.ac.mjc.menutest.korean;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.mjc.menutest.R;

public class kor_Temp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_kor_temp);

        Button coldBtn = findViewById(R.id.cold_btn);

        coldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_Temp.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button hotBtn = findViewById(R.id.hot_btn);

        hotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_Temp.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
    }
}
