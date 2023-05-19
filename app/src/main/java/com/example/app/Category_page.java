package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Category_page extends AppCompatActivity {
    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);
        Bundle bundle = getIntent().getExtras();
        account = bundle.getString("account");


    }

    public void onClickBtn(View view) {
        System.out.println(account+"1");
        Intent intent = new Intent(Category_page.this, meal_Select.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClickBtnJust_page(View view) {
        Intent intent = new Intent(Category_page.this, Just_page.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onClickToCheckOutPage(View view) {
        Intent intent = new Intent(Category_page.this, Checkout_page.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}