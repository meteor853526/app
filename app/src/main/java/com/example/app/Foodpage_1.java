package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Foodpage_1 extends AppCompatActivity {

    MySQLhandler mySQLhandler = new MySQLhandler();
    private String combo = "combo1";
    private int price=100;



    public Foodpage_1() throws SQLException {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodpage1);




    }


    public void addButton(View v) throws SQLException, ClassNotFoundException {


        new Thread (new Runnable(){

            @Override
            public void run() {
                mySQLhandler.run();
                try {
                  mySQLhandler.toCart(combo,price);


                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }
}
