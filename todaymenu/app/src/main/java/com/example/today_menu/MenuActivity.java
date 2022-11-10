package com.example.today_menu;

        import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.example.today_menu.R;

public class MenuActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        // 툴바 활성화
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 햄버거 버튼 이미지 불러오기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_others);
        // 툴바에 적힐 제목
        actionBar.setTitle("오늘 뭐 먹지?");
        actionBar.setHomeButtonEnabled(true);

        Button pickBtn = findViewById(R.id.question_btn);

        pickBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://doda.app/quiz/P54xRQM70K"));

            }
        });

    }
}