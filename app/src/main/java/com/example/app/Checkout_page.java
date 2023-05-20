
        package com.example.app;

        import androidx.appcompat.app.AppCompatActivity;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;

public class Checkout_page extends AppCompatActivity {
    private ListView lvMainMeals;
    MySQLhandler sqLhandler = new MySQLhandler();

    private String account;

    public Checkout_page() throws SQLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("account");


        new Thread(new Runnable() {
            @Override
            public void run() {

                sqLhandler.run();
                final ResultSet data;
                Bundle bundle = getIntent().getExtras();
                String username = bundle.getString("account");
                account = username;
                try {
                    data = sqLhandler.getUserCurrentOrder(username);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                updateCurrentShoppingList(data);
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


        lvMainMeals = findViewById(R.id.lv_checkout_meals);
        lvMainMeals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = getIntent().getExtras();
                String username = bundle.getString("account");
                Log.v("DB","wwwwwwwwwww");
                TextView food_name = (TextView) view.findViewById(R.id.tv_meal_name);
                TextView food_count = view.findViewById(R.id.tv_count);
                new Thread (new Runnable(){
                    ResultSet current_order;
                    @Override
                    public void run(){
                        sqLhandler.run();
                        try {
                            sqLhandler.minusItemCountIntoShopping_car(username,food_name.getText().toString(),Integer.parseInt(food_count.getText().toString().replace("個","")));
                            current_order = sqLhandler.getUserCurrentOrder(username);
                            //tv_count
                            runOnUiThread(new Runnable() {
                                @SuppressLint("SetTextI18n")
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

    @SuppressLint("SetTextI18n")
    private void updateCurrentShoppingList(ResultSet currentOrder) throws SQLException, ClassNotFoundException {
        lvMainMeals = findViewById(R.id.lv_checkout_meals);
        TextView tv_totalPrice = findViewById(R.id.tv_totalPrice);
        int totalPrice = 0;
        List<FoodItem> foods = new ArrayList<FoodItem>();
        while (currentOrder.next()){
            String food_name = currentOrder.getString("food_name");
            String price = currentOrder.getString("price");
            int count = currentOrder.getInt("count");
            totalPrice += Integer.parseInt(price) * count;
            foods.add(new FoodItem(R.drawable.food1,food_name ,Integer.parseInt(price),Integer.toString(count) + "個"));
            Log.v("DB",price);
        }
        tv_totalPrice.setText("總共: "+ Integer.toString(totalPrice) + " $");
        ListViewAdapter adapter1 = new ListViewAdapter(this,foods);
        lvMainMeals.setAdapter(adapter1);
    }


    public void onClickToCategory(View view) {
        Intent intent = new Intent(Checkout_page.this, Category_page.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}