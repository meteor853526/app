package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Just_page extends AppCompatActivity {
    private ListView lvMainMeals;
    MySQLhandler sqLhandler = new MySQLhandler();

    public Just_page() throws SQLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_page);
        lvMainMeals = findViewById(R.id.lv_main_meals);
        new Thread (new Runnable(){
            @Override
            public void run(){

                sqLhandler.run();
                final ResultSet data;
                try {
                    data = sqLhandler.getData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                updateMeals(data);

                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    });
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                //Log.v("OK",data);
//        text_view.post(new Runnable() {
//          public void run() {
//            text_view.setText(data);
//          }
//        });

            }
        }).start();

    }



    private void updateMeals(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        lvMainMeals = findViewById(R.id.lv_main_meals);
        List<FoodItem> foods = new ArrayList<FoodItem>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String introduce = resultSet.getString("introduce");
            String price = resultSet.getString("price");
            foods.add(new FoodItem(R.drawable.food1,introduce ,Integer.parseInt(price)));
            Log.v("DB",price);
        }
        ListViewAdapter adapter1 = new ListViewAdapter(this,foods);
        lvMainMeals.setAdapter(adapter1);
    }
}