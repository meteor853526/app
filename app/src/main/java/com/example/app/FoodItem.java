package com.example.app;

import android.graphics.Bitmap;

public class FoodItem {


    private Bitmap imageId;
    private String foodName;
    private int foodPrice;


    public FoodItem() {
    }

    public FoodItem(Bitmap imageId, String foodName, int foodPrice) {
        this.imageId = imageId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public Bitmap getImageId() {
        return imageId;
    }

    public void setImageId(Bitmap imageId) {
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



