package com.example.today_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("MainActivity","onCreate");

        Intent intent1 = getIntent();
        String email = intent1.getStringExtra("email");
        String password = intent1.getStringExtra("password");

        EditText emailEt=findViewById(R.id.email_et);
        emailEt.setText(email);

        EditText passwordEt=findViewById(R.id.password_et);
        passwordEt.setText(password);

        Button loginBtn=findViewById(R.id.login_btn);
        Button joinBtn=findViewById(R.id.join_btn);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,JoinActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            }
        });
    }
}
