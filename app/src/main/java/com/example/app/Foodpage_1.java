package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class Foodpage_1 extends AppCompatActivity {

    MySQLhandler mySQLhandler = new MySQLhandler();
    private String combo = "西式摩登套餐";
    private int price=100;
    private String account;

    private int count=1;
    private Button subBtn, addBtn;
    private TextView tvNumber, tvPrice;
    int totalprice=100;

    public Foodpage_1() throws SQLException {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodpage1);
        addBtn=findViewById(R.id.add);
        subBtn=findViewById(R.id.sub_Btn);
        tvNumber=findViewById(R.id.tv_Number);
        tvPrice=findViewById(R.id.tv_Price);
        Bundle bundle = getIntent().getExtras();
        account = bundle.getString("account");
        View.OnClickListener onClickListener_1=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count+=1;
                tvNumber.setText(""+count);
                totalprice=price*count;
                tvPrice.setText("total: $"+totalprice);

            }
        };
        addBtn.setOnClickListener(onClickListener_1);

        View.OnClickListener onClickListener_2=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count-=1;
                if (count<1){
                    count=1;
                };
                totalprice=price*count;
                tvPrice.setText("total: $"+totalprice);
                tvNumber.setText(""+count);
            }
        };
        subBtn.setOnClickListener(onClickListener_2);

    }

    public void addtoCart(View v) throws SQLException, ClassNotFoundException {
        System.out.println(account+"3");


        new Thread (new Runnable(){

            @Override
            public void run() {
                mySQLhandler.run();
                try {
                  mySQLhandler.toCart(account,combo,count,price);




                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }///
        }).start();
        Toast.makeText(this, "加入購物車成功", Toast.LENGTH_LONG).show();

    }
    public void onClickToCategory_page(View view) {
        Intent intent = new Intent(Foodpage_1.this, Category_page.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onClickToCheckOutPage(View view) {
        Intent intent = new Intent(Foodpage_1.this, Checkout_page.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
