package com.example.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<FoodItem> ListFoods;

    public ListViewAdapter(Context context, List<FoodItem> listFoods) {
        this.context = context;
        this.ListFoods = listFoods;
    }

    @Override
    public int getCount() {
        return ListFoods.size();
    }

    @Override
    public Object getItem(int i) {
        return ListFoods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.meal_item,viewGroup,false);
        }

        FoodItem food = ListFoods.get(i);
        ImageView iv = view.findViewById(R.id.iv_food);
        iv.setImageResource(food.getImageId());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        TextView tvFoodName = view.findViewById(R.id.tv_meal_name);
        tvFoodName.setText(food.getFoodName());
        TextView tvFoodPrice = view.findViewById(R.id.tv_meal_price);
        TextView tvCount = view.findViewById(R.id.tv_count);
        tvCount.setText(food.getCount());
        tvFoodPrice.setText(String.valueOf(food.getFoodPrice()) + "  $");
        iv.setClipToOutline(true);
        return view;
    }
}
