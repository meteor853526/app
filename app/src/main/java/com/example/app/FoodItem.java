package com.example.app;

import android.graphics.Bitmap;

public class FoodItem {


    private int imageId;
    private String foodName;
    private int foodPrice;
    private int count;

    public FoodItem() {
    }

    public FoodItem(int imageId, String foodName, int foodPrice,int count) {
        this.imageId = imageId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }



}



