package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Foodpage_1 extends AppCompatActivity {

    MySQLhandler mySQLhandler = new MySQLhandler();
    private String combo = "combo1";
    private int price=100;
    private String account;
    private ImageView addButton, subButton;
    private int count=1;




    public Foodpage_1() throws SQLException {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_foodpage1);
        Bundle bundle = getIntent().getExtras();
        account = bundle.getString("account");



    }
    public void addBtn(View v) throws SQLException, ClassNotFoundException {
        count+=1;
        Toast.makeText(this, "Clicked on addButton"+count, Toast.LENGTH_LONG).show();
    }
    public void subBtn(View v) throws SQLException, ClassNotFoundException {
        count-=1;
        Toast.makeText(this, "Clicked on subButton"+count, Toast.LENGTH_LONG).show();
    }




    public void addButton(View v) throws SQLException, ClassNotFoundException {
        System.out.println(account+"3");

        new Thread (new Runnable(){

            @Override
            public void run() {
                mySQLhandler.run();
                try {
                  mySQLhandler.toCart(account,combo,price);


                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }
}
