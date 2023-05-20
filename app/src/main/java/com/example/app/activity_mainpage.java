package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_mainpage extends AppCompatActivity {
    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        Bundle bundle = getIntent().getExtras();
        account = bundle.getString("account");

    }
    public void onClickBtn(View view) {
        System.out.println(account+"2");
        Intent intent = new Intent(this, Foodpage_1.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClickBtn_2(View view) {
        System.out.println(account+"2_2");
        Intent intent = new Intent(this, Foodpage2.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onClickBtn_3(View view) {
        System.out.println(account+"2_3");
        Intent intent = new Intent(this, Foodpage3.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}