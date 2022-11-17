package kr.ac.mjc.menutest.japanese;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.mjc.menutest.R;
import kr.ac.mjc.menutest.korean.KoreanActivity;

public class jap_DegreeTemp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kor_degree_temp);

        Button coldBtn = findViewById(R.id.cold_btn);

        coldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jap_DegreeTemp.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button hotBtn = findViewById(R.id.hot_btn);

        hotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jap_DegreeTemp.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
    }
}
