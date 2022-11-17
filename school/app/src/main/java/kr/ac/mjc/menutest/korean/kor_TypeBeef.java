package kr.ac.mjc.menutest.korean;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.mjc.menutest.R;

public class kor_TypeBeef extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kor_type_beef);

        Button cowBtn = findViewById(R.id.cow_btn);

        cowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_TypeBeef.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button pigBtn = findViewById(R.id.pig_btn);

        pigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_TypeBeef.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button chickenBtn = findViewById(R.id.chicken_btn);

        chickenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_TypeBeef.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
    }
}
