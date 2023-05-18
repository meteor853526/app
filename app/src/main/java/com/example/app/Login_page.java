package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;


public class Login_page extends AppCompatActivity {
    private EditText sign_email;
    private EditText sign_password;
    MySQLhandler mySQLhandler = new MySQLhandler();
    public boolean a;


    public Login_page() throws SQLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        sign_email = findViewById(R.id.Signup_email);
        sign_password = findViewById(R.id.Signup_password);
    }

    public void onClickBtn(View v) throws SQLException, ClassNotFoundException {
        String email=sign_email.getText().toString();
        String password=sign_password.getText().toString();
        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "請重新輸入帳號密碼", Toast.LENGTH_LONG).show();
            return;
        }



        new Thread (new Runnable(){

            @Override
            public void run() {
                mySQLhandler.run();
                try {
                    boolean isMember=mySQLhandler.CheckAccount(email,password);

                    if(isMember){
                        Intent intent = new Intent(Login_page.this, Category_page.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Login_page.this, Signup_page.class);
                        startActivity(intent);
                    }


                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }


    public void onClickBtn2(View view) {
        Intent intent = new Intent(Login_page.this, Signup_page.class);
        startActivity(intent);
    }
}