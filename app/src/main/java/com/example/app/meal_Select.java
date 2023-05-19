package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class meal_Select extends AppCompatActivity {
    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_select);
    }
    public void onClickBtn(View view) {
        System.out.println(account+"2");
        Intent intent = new Intent(this, Foodpage_1.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}