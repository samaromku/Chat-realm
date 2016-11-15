package ru.intefor.chat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.intefor.chat.R;
import ru.intefor.chat.entities.AuthPayload;
import ru.intefor.chat.entities.RequestContainer;
import ru.intefor.chat.utils.HashUtil;

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
            /*Intent intent = new Intent(RegActivity.this, LoginActivity.class);
            intent.putExtra("login", mEnterLogin.getText().toString());
            intent.putExtra("password", mEnterPassword.getText().toString());
            startActivity(intent);*/
            doReg();
        }
    }

    private void doReg(){
        String login = mEnterLogin.getText().toString();
        String password = HashUtil.hash(mEnterPassword.getText().toString());
        System.out.println("логин и пароль"+ login + " " + password);
        AuthPayload payload = new AuthPayload(login, password);
        final RequestContainer<AuthPayload> requestContainer = new RequestContainer<>(payload);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(requestContainer));
                Request request = new Request.Builder()
                        .url("http://91.122.56.48:8084/levelupchat/register")
                        .post(body)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    Log.e(RegActivity.class.getSimpleName(), Log.getStackTraceString(e));
                }
//                try {
//                    String hello = "hello";
//                    URL url = new URL("http://91.122.56.48:8080/levelupchat/register");
//                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//                    connection.setRequestMethod("POST");
//                    connection.setDoInput(true);
//                    connection.setDoOutput(true);
//                    connection.setRequestProperty("Content-Length", String.valueOf(hello.getBytes().length));
//                    OutputStream os = connection.getOutputStream();
//                    os.write(hello.getBytes("UTF-8"));
//                    connection.connect();
//                    int responseCode = connection.getResponseCode();
//                    InputStream is = connection.getInputStream();
//                    ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
//                    byte[] buffer = new byte[4096];
//                    int length;
//                    while ((length = is.read(buffer))!=-1){
//                        byteArrayStream.write(buffer,0,length);
//                    }
//                    String response = byteArrayStream.toString();
//                    System.out.println(response);
//                    Log.d(RegActivity.class.getSimpleName(), "response-code: " + response);
//                    is.close();
//
//
//                } catch (java.io.IOException e) {
//                    Log.e(RegActivity.class.getSimpleName(), Log.getStackTraceString(e));
//                }
            }
        }).start();
    }
}
