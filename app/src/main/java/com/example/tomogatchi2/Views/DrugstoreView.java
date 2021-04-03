package com.example.tomogatchi2.Views;

import android.app.Activity;
import android.os.Bundle;

import com.example.tomogatchi2.Controllers.DrugstoreController;
import com.example.tomogatchi2.Models.ShopItem;
import com.example.tomogatchi2.R;
import com.example.tomogatchi2.Utils.RecyclerViewAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DrugstoreView extends AppCompatActivity {

    RecyclerView recyclerView;
    String drugType[];
    Activity thisActivity;
    DrugstoreController drugstoreController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrugstoreController drugstoreController = new DrugstoreController();

        recyclerView = findViewById(R.id.recyclerView2);

        ShopItem[] shopItems = new ShopItem[2];
        shopItems[0] = new ShopItem();
        shopItems[1] = new ShopItem();

        drugType = getResources().getStringArray(R.array.drugs);
        shopItems[0].name = drugType[0];
        shopItems[1].name = drugType[1];

        shopItems[0].price = drugstoreController.getBandagePrice();
        shopItems[1].price = drugstoreController.getPillsPrice();

        shopItems[0].owned = drugstoreController.getBandageOwned();
        shopItems[1].owned = drugstoreController.getPillsOwned();

        shopItems[0].image = R.drawable.bandage;
        shopItems[1].image = R.drawable.pills;

        RecyclerViewAdapter recAdapter = new RecyclerViewAdapter(this, shopItems, thisActivity);
        recyclerView.setAdapter(recAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}