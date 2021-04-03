package com.example.tomogatchi2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tomogatchi2.Models.ShopItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class Drugstore extends AppCompatActivity {

    RecyclerView recyclerView;
    String drugType[];
    SharedPreferences sharedPreferences;
    Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        thisActivity = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.recyclerView2);

        ShopItem[] shopItems = new ShopItem[2];
        shopItems[0] = new ShopItem();
        shopItems[1] = new ShopItem();

        drugType = getResources().getStringArray(R.array.drugs);
        shopItems[0].name = drugType[0];
        shopItems[1].name = drugType[1];

        shopItems[0].price = String.valueOf(sharedPreferences.getInt(Data.BandagePrice, 0));
        shopItems[1].price = String.valueOf(sharedPreferences.getInt(Data.PillsPrice, 0));

        shopItems[0].owned = String.valueOf(sharedPreferences.getInt(Data.Bandage, 0));
        shopItems[1].owned = String.valueOf(sharedPreferences.getInt(Data.Pills, 0));

        shopItems[0].image = R.drawable.bandage;
        shopItems[1].image = R.drawable.pills;

        RecyclerViewAdapter recAdapter = new RecyclerViewAdapter(this, shopItems, thisActivity);
        recyclerView.setAdapter(recAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}