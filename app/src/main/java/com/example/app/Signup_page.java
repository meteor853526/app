package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Signup_page extends AppCompatActivity {
    private EditText sign_email;
    private EditText sign_password;
    private Button sign_login_button;
    MySQLhandler mySQLhandler = new MySQLhandler();


    public Signup_page() throws SQLException {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        sign_email = findViewById(R.id.Signup_email);
        sign_password = findViewById(R.id.Signup_password);
    }
    //將使用者帳戶送入資料庫
    public void onClickBtn_Signup(View view) {
        new Thread (new Runnable(){
            @Override
            public void run() {
                mySQLhandler.run();
                try {
                    String email=sign_email.getText().toString();
                    String password=sign_password.getText().toString();
                    //產生一組hash過後的密碼
                    String password_hash=BCrypt.hashpw(password,BCrypt.gensalt());
                    mySQLhandler.CreateUser(email,password_hash);

                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        Intent intent = new Intent(Signup_page.this, Login_page.class );
        startActivity(intent);
    }
}