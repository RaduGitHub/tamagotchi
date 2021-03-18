package com.example.tomogatchi2;

import android.os.Bundle;

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
    String s1[], s2[], s3[];
    int images[] = {R.drawable.pizza, R.drawable.ice_cream, R.drawable.fries, R.drawable.cookie,
            R.drawable.soda, R.drawable.hot_dog};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.food_types);
        s2 = getResources().getStringArray(R.array.food_price);
        s3 = getResources().getStringArray(R.array.food_price);

        RecyclerViewAdapter recAdapter = new RecyclerViewAdapter(this, s1, s2, s3, images);
        recyclerView.setAdapter(recAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}