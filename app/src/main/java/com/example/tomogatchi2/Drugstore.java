package com.example.tomogatchi2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class Drugstore extends AppCompatActivity {

    RecyclerView recyclerView;
    String drugType[], drugPrice[], drugOwned[];
    int images[] = {R.drawable.bandage, R.drawable.pills};
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.recyclerView2);


        drugType = getResources().getStringArray(R.array.drugs);

        drugPrice = new String[2];
        drugPrice[0] = String.valueOf(sharedPreferences.getInt(Data.BandagePrice, 0));
        drugPrice[1] = String.valueOf(sharedPreferences.getInt(Data.PillsPrice, 0));

        drugOwned = new String[2];
        drugOwned[0] = String.valueOf(sharedPreferences.getInt(Data.Bandage, 0));
        drugOwned[1] = String.valueOf(sharedPreferences.getInt(Data.Pills, 0));

        RecyclerViewAdapter recAdapter = new RecyclerViewAdapter(this, drugType, drugPrice, drugOwned, images);
        recyclerView.setAdapter(recAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}