package ru.intefor.chat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.intefor.chat.R;

public class RegActivity extends Activity{
    private Button mLogin;
    private Button mRegistration;
    private EditText mEnterLogin;
    private EditText mEnterPassword;
    private EditText mConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        mEnterLogin = (EditText) findViewById(R.id.enter_login);
        mEnterPassword = (EditText) findViewById(R.id.enter_password);
        mConfirmPassword = (EditText) findViewById(R.id.confirm_password);
        mRegistration = (Button) findViewById(R.id.reg_button);
        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkData();
            }
        });

        mLogin = (Button) findViewById(R.id.login_button);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkData(){
        if(!mEnterPassword.getText().toString().equals(mConfirmPassword.getText().toString())){
            Toast.makeText(getApplicationContext(), "Пароли не совпадают! Исправляй", Toast.LENGTH_SHORT).show();
        }
        else if(mEnterPassword.getText().toString().length()<8){
            Toast.makeText(getApplicationContext(), "Пароль меньше 8 символов!", Toast.LENGTH_SHORT).show();
        }
        else if(mEnterLogin.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Заполните логин", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(RegActivity.this, LoginActivity.class);
            intent.putExtra("login", mEnterLogin.getText().toString());
            intent.putExtra("password", mEnterPassword.getText().toString());
            startActivity(intent);
        }
    }
}
