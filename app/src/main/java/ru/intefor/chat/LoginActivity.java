package ru.intefor.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button mLogin;
    private Button mRegistration;
    private EditText mEnterLogin;
    private EditText mEnterPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mLogin = (Button) findViewById(R.id.login_button);
        mEnterLogin = (EditText) findViewById(R.id.enter_login);
        mEnterPassword = (EditText) findViewById(R.id.enter_password);
        fillFields();
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkData();
            }
        });

        mRegistration = (Button) findViewById(R.id.reg_button);
        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkData(){
        if(mEnterLogin.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), R.string.login_empty, Toast.LENGTH_SHORT).show();
        }
        else if(mEnterPassword.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), R.string.password_empty, Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(getApplicationContext(), R.string.toast_text_login_correct, Toast.LENGTH_SHORT).show();
        }
    }

    public void fillFields(){
        Intent intent = getIntent();
        String log = intent.getStringExtra("login");
        String pas = intent.getStringExtra("password");
        System.out.println(log + " " + pas);
        if(log!=null&& pas!=null){
            mEnterLogin.setText(log);
            mEnterPassword.setText(pas);
        }
    }

}
