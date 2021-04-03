package com.example.tomogatchi2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.Models.ShopItem;
import com.example.tomogatchi2.Utils.RecyclerViewAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Shop extends AppCompatActivity {

    RecyclerView recyclerView;
    String foodType[], foodPrice[], foodOwned[];
    int images[] = {R.drawable.pizza, R.drawable.ice_cream, R.drawable.fries, R.drawable.cookie,
            R.drawable.soda, R.drawable.hot_dog};
    SharedPreferences sharedPreferences;
    Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        thisActivity = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.recyclerView);
        ShopItem[] shopItem = new ShopItem[7];

        foodType = getResources().getStringArray(R.array.foodTypes);

        foodPrice = new String[sharedPreferences.getInt(Data.FoodstoreTotalItems, 0)];
        foodPrice[0] = String.valueOf(sharedPreferences.getInt(Data.PizzaPrice, 0));
        foodPrice[1] = String.valueOf(sharedPreferences.getInt(Data.IceCreamPrice, 0));
        foodPrice[2] = String.valueOf(sharedPreferences.getInt(Data.FriesPrice, 0));
        foodPrice[3] = String.valueOf(sharedPreferences.getInt(Data.CookiePrice, 0));
        foodPrice[4] = String.valueOf(sharedPreferences.getInt(Data.SodaPrice, 0));
        foodPrice[5] = String.valueOf(sharedPreferences.getInt(Data.HotDogPrice, 0));

        foodOwned = new String[sharedPreferences.getInt(Data.FoodstoreTotalItems, 0)];
        foodOwned[0] = String.valueOf(sharedPreferences.getInt(Data.Pizza, 0));
        foodOwned[1] = String.valueOf(sharedPreferences.getInt(Data.IceCream, 0));
        foodOwned[2] = String.valueOf(sharedPreferences.getInt(Data.Fries, 0));
        foodOwned[3] = String.valueOf(sharedPreferences.getInt(Data.Cookie, 0));
        foodOwned[4] = String.valueOf(sharedPreferences.getInt(Data.Soda, 0));
        foodOwned[5] = String.valueOf(sharedPreferences.getInt(Data.HotDog, 0));



        RecyclerViewAdapter recAdapter = new RecyclerViewAdapter(this, shopItem, thisActivity);
        recyclerView.setAdapter(recAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}