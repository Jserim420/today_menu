package com.example.today_menu;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class JoinActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        EditText emailEt=findViewById(R.id.email_et);
        EditText passwordEt=findViewById(R.id.password_et);
        EditText passwordConfirmEt=findViewById(R.id.password_confirm_et);
        Button joinBtn = findViewById(R.id.join_btn);


        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();
                String passwordConfirm = passwordConfirmEt.getText().toString();

                if(email.equals("이메일을 입력해주세요.")) {
                    Toast.makeText(JoinActivity.this, "이메일을 입력해 주세요.", Toast.LENGTH_LONG).show();
                }
                if(!email.contains("@")) {
                    Toast.makeText(JoinActivity.this, "올바른 이메일 방식으로 입력해 주세요.", Toast.LENGTH_LONG).show();
                }
                if(password.equals("")) {
                    Toast.makeText(JoinActivity.this, "비밀번호를 입력해 주세요.", Toast.LENGTH_LONG).show();
                }
                if(!password.equals(passwordConfirm)) {
                    Toast.makeText(JoinActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }

                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser user = authResult.getUser();

                                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);

                                intent.putExtra("email", email);
                                intent.putExtra("password", password);

                                startActivity(intent);

                                Toast.makeText(JoinActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("JoinActivity", e.getMessage());
                                Toast.makeText(JoinActivity.this, "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}