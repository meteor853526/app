package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Signup_page extends AppCompatActivity {
    private ListView lvMainMeals;
    MySQLhandler mySQLhandler = new MySQLhandler();

    public Signup_page() throws SQLException {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        lvMainMeals = findViewById(R.id.lv_main_meals);
        try {
            updateMeals();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void updateMeals() throws SQLException, ClassNotFoundException {

        //Cursor cursor = databaseHandler.getAllMeals();
        //SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.meal_item, cursor, new String[] {"name", "price"}, new int[] {R.id.tv_meal_name,R.id.tv_meal_price},0);
        try {
            FileInputStream mealImgInput = openFileInput("hb.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(mealImgInput);
            //MainActivity.image = bitmap;
            mealImgInput.close();
            // iv_food.setImageBitmap(bitmap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<FoodItem> foods = new ArrayList<FoodItem>();
        ResultSet resultSet = mySQLhandler.getData();

        while (resultSet.next()){
            String mealname = resultSet.getString(1);
            String mealprice = resultSet.getString(3);
            foods.add(new FoodItem(R.drawable.food1,mealname ,Integer.parseInt(mealprice)));
        }
        ListViewAdapter adapter1 = new ListViewAdapter(this,foods);
        lvMainMeals.setAdapter(adapter1);
    }
}