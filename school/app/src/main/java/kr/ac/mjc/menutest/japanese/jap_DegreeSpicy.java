package kr.ac.mjc.menutest.japanese;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.mjc.menutest.R;
import kr.ac.mjc.menutest.korean.KoreanActivity;

public class jap_DegreeSpicy extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jap_degree_spicy);

        Button spicyBtn = findViewById(R.id.spicy_btn);

        spicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jap_DegreeSpicy.this, KoreanActivity.class);
                startActivity(intent);
            }
        });

        Button nonspicyBtn = findViewById(R.id.nonspicy_btn);

        nonspicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jap_DegreeSpicy.this, KoreanActivity.class);
                startActivity(intent);
            }
        });
    }
}
