package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
        //將使用者帳戶送入資料庫
        sign_email=findViewById(R.id.Signup_email);
        sign_password=findViewById(R.id.Signup_password);
        sign_login_button=findViewById(R.id.signup_login);
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread (new Runnable(){
                    @Override
                    public void run() {
                        mySQLhandler.run();
                        try {
                            String email=sign_email.getText().toString();
                            String password=sign_password.getText().toString();
                            mySQLhandler.CreateUser(email,password);
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
//                try {
//                    String email=sign_email.getText().toString();
//                    String password=sign_password.getText().toString();
//                    mySQLhandler.CreateUser(email,password);
//                } catch (SQLException | ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
            }
        };
        sign_login_button.setOnClickListener(onClickListener);


    }



}