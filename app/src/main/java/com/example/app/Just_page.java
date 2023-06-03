package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Just_page extends AppCompatActivity {
    private ListView lvMainMeals;
    private TextView tv_currentOrder;
    MySQLhandler sqLhandler = new MySQLhandler();

    Button add_btn;
    Button minus_btn;
    TextView category_bar;
    private String account;
    public Just_page() throws SQLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_page);
        lvMainMeals = findViewById(R.id.lv_main_meals);
        category_bar = findViewById(R.id.category);
//        add_btn = findViewById(R.id.add_btn);
//        minus_btn = findViewById(R.id.minus_btn);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("account");
        String category = bundle.getString("category");
        account = username;

        if(Objects.equals(category, "just")){
            category_bar.setText("單點區");
        }else if(Objects.equals(category, "drink")){
            category_bar.setText("飲料區");
        }else if(Objects.equals(category, "sneak")){
            category_bar.setText("點心區");
        }
        new Thread (new Runnable(){
            @Override
            public void run(){

                sqLhandler.run();
                ResultSet data;
                ResultSet current_order;
                try {

                    data = sqLhandler.getData(category);
                    current_order = sqLhandler.getUserCurrentOrder(username);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                updateMeals(data);
                                updateCurrentShoppingList(current_order);

                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    });
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        lvMainMeals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = getIntent().getExtras();
                String username = bundle.getString("account");
                Log.v("DB","wwwwwwwwwww");
                TextView food_name = (TextView) view.findViewById(R.id.tv_meal_name);
                TextView tv_meal_price = (TextView) view.findViewById(R.id.tv_meal_price);
                new Thread (new Runnable(){
                    ResultSet current_order;
                    @Override
                    public void run(){
                        sqLhandler.run();
                        tv_currentOrder = findViewById(R.id.tv_currentOrder);


                        try {
                            sqLhandler.addItemCountIntoShopping_car(username,food_name.getText().toString(),Integer.parseInt(tv_meal_price.getText().toString().replace(" ","").replace("$","")));
                            current_order = sqLhandler.getUserCurrentOrder(username);
                            //tv_count
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    sqLhandler.run();
                                    try {
                                        updateCurrentShoppingList(current_order);
                                    } catch (ClassNotFoundException | SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                        } catch (ClassNotFoundException | SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        });


    }
    public void onMinusBtn(View v)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Just_page.this, Login_page.class);
        startActivity(intent);
    }

    public void onAddBtn(View v)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Just_page.this, Login_page.class);
        startActivity(intent);
    }


    private void updateMeals(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        lvMainMeals = findViewById(R.id.lv_main_meals);
        List<FoodItem> foods = new ArrayList<FoodItem>();
        while (resultSet.next()) {
            String food_name = resultSet.getString("food_name");
            Blob byteArray = resultSet.getBlob("food_image");
            byte[] arr = byteArray.getBytes(1L, (int) byteArray.length());
            Bitmap bm = BitmapFactory.decodeByteArray(arr, 0 , (int) byteArray.length());
            String price = resultSet.getString("price");
            int count = 0;
            foods.add(new FoodItem(R.drawable.food1, food_name, bm, Integer.parseInt(price), ""));
        }
        ListViewAdapter adapter1 = new ListViewAdapter(this,foods);
        lvMainMeals.setAdapter(adapter1);
    }
    private void updateCurrentShoppingList(ResultSet currentOrder) throws SQLException, ClassNotFoundException {
        tv_currentOrder = findViewById(R.id.tv_currentOrder);
        StringBuilder str = new StringBuilder("");
        while(currentOrder.next()){
            //tv_currentOrder
            String food_name = currentOrder.getString("food_name");
            Log.v("DB",food_name);
            int count = currentOrder.getInt("count");
            str.append(food_name).append(" x ").append(Integer.toString(count)).append(",");
        }
        tv_currentOrder.setText(str);
    }

    public void onClickToCategory_page(View view) {
        Intent intent = new Intent(Just_page.this, Category_page.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}