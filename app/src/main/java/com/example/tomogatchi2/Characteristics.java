package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.tomogatchi2.Data.MyPREFERENCES;

public class Characteristics extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView happiness, name, height, weight, daysAlive, health, coins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characteristics);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //Set Happiness level
        happiness = findViewById(R.id.hapinessProp);
        happiness.setText(String.valueOf(sharedPreferences.getInt(Data.Happiness, 0)));

        //Set Name
        name = findViewById(R.id.nameCaracterProp);
        name.setText(String.valueOf(sharedPreferences.getString(Data.Name, "def")));

        //Set Height
        height = findViewById(R.id.heightCaracterProp);
        height.setText(String.valueOf(sharedPreferences.getInt(Data.Height, 0)));

        //Set Weight
        weight = findViewById(R.id.weightCaracterProp);
        weight.setText(String.valueOf(sharedPreferences.getInt(Data.Weight, 0)));

        //Set DaysAlive
        daysAlive = findViewById(R.id.daysAliveProp);
        daysAlive.setText(String.valueOf(sharedPreferences.getInt(Data.DaysAlive, 0)));

        //Set Health
        health = findViewById(R.id.healthProp);
        health.setText(String.valueOf(sharedPreferences.getInt(Data.Health, 0)));

        //Set Coins
        coins = findViewById(R.id.coinsCaracterProp);
        coins.setText(String.valueOf(sharedPreferences.getInt(Data.Money, 0)));

    }
}