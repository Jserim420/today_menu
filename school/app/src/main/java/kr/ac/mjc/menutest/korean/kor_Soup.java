package kr.ac.mjc.menutest.korean;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.mjc.menutest.R;

public class kor_Soup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupick_kor_soup);

        Button spicyBtn = findViewById(R.id.spicy_btn);

        spicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_Soup.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button nonspicyBtn = findViewById(R.id.nonspicy_btn);

        nonspicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_Soup.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button coolBtn = findViewById(R.id.cool_btn);

        coolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_Soup.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
    }
}
