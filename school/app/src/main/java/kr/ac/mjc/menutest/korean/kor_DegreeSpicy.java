package kr.ac.mjc.menutest.korean;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.mjc.menutest.R;

public class kor_DegreeSpicy extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kor_degree_spicy);

        Button spicyBtn = findViewById(R.id.spicy_btn);

        spicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_DegreeSpicy.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button nonspicyBtn = findViewById(R.id.nonspicy_btn);

        nonspicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kor_DegreeSpicy.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
    }
}
