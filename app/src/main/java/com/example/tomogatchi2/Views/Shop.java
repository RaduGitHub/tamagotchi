package com.example.tomogatchi2.Views;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tomogatchi2.Controllers.ShopController;
import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.Models.ShopItem;
import com.example.tomogatchi2.R;
import com.example.tomogatchi2.Utils.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class Shop extends AppCompatActivity {

    RecyclerView recyclerView;
    String foodType[], foodPrice[], foodOwned[];
    /*int images[] = {R.drawable.pizza, R.drawable.ice_cream, R.drawable.fries, R.drawable.cookie,
            R.drawable.soda, R.drawable.hot_dog};*/
    SharedPreferences sharedPreferences;
    Activity thisActivity;
    ShopController shopController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        thisActivity = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);
        shopController = new ShopController();

        recyclerView = findViewById(R.id.recyclerView);
        ShopItem[] shopItem = new ShopItem[6];
        shopItem[0] = new ShopItem();
        shopItem[1] = new ShopItem();
        shopItem[2] = new ShopItem();
        shopItem[3] = new ShopItem();
        shopItem[4] = new ShopItem();
        shopItem[5] = new ShopItem();

        foodType = getResources().getStringArray(R.array.foodTypes);

        //shopItem Name
        shopItem[0].name = foodType[0];
        shopItem[1].name = foodType[1];
        shopItem[2].name = foodType[2];
        shopItem[3].name = foodType[3];
        shopItem[4].name = foodType[4];
        shopItem[5].name = foodType[5];

        shopItem[0].price = shopController.getPizzaPrice();
        shopItem[1].price = shopController.getIcePrice();
        shopItem[2].price = shopController.getFriesPrice();
        shopItem[3].price = shopController.getCookiePrice();
        shopItem[4].price = shopController.getSodaPrice();
        shopItem[5].price = shopController.getHotDogPrice();

        shopItem[0].owned = shopController.getPizzaOwned();
        shopItem[1].owned = shopController.getIceOwned();
        shopItem[2].owned = shopController.getFriesOwned();
        shopItem[3].owned = shopController.getCookieOwned();
        shopItem[4].owned = shopController.getSodaOwned();
        shopItem[5].owned = shopController.getHotDogOwned();

        shopItem[0].image = R.drawable.pizza;
        shopItem[1].image = R.drawable.ice_cream;
        shopItem[2].image = R.drawable.fries;
        shopItem[3].image = R.drawable.cookie;
        shopItem[4].image = R.drawable.soda;
        shopItem[5].image = R.drawable.hot_dog;

        RecyclerViewAdapter recAdapter = new RecyclerViewAdapter(this, shopItem, thisActivity);
        recyclerView.setAdapter(recAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}