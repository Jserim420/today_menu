package com.example.today_menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

public class ChangePwPopup extends Activity {

    FirebaseAuth firebaseAuth ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_change_pw);

        firebaseAuth = FirebaseAuth.getInstance();

        EditText changeEt = findViewById(R.id.ch_password_et);
        EditText confirmEt = findViewById(R.id.ch_password_confirm_et);
        Button changeBtn = findViewById(R.id.upload_btn);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String changePw = changeEt.getText().toString();
                String confirmPw = confirmEt.getText().toString();

                Log.d("Change Password", changePw + ", " + confirmPw);

                if (changePw.length() < 6) {
                    if (confirmPw.length() < 6) {
                        Log.d("Change Password", changePw + ", " + confirmPw);
                        Toast.makeText(ChangePwPopup.this, "비밀번호는 6자 이상이어야 합니다.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (!changePw.equals(confirmPw)) {
                        Log.d("Change Password", changePw + ", " + confirmPw);
                        Toast.makeText(ChangePwPopup.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    }
                    if (changePw.equals(confirmPw)) {
                        Log.d("Change Password", changePw + ", " + confirmPw);
                        firebaseAuth.getCurrentUser().updatePassword(changeEt.getText().toString());
                        Toast.makeText(ChangePwPopup.this, "비밀번호가 변경되었습니다.", Toast.LENGTH_LONG).show();
                        Log.d("Change Password", changeEt.getText().toString());
                        mOnClose(view);
                    }
                }
            }
        });

    }

    public void mOnClose(View v) {
        Intent intent = new Intent();
        Log.d("Result", "Complete Change Password");
        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
