package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tomogatchi2.Models.CharacteristicModel;
import com.example.tomogatchi2.Models.Data;

import static com.example.tomogatchi2.Models.Data.MyPREFERENCES;

public class Characteristics extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView happiness, name, height, weight, daysAlive, health, coins;
    CharacteristicModel characteristicModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characteristics);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        characteristicModel = new CharacteristicModel(sharedPreferences);


        //Set Happiness level
        happiness = findViewById(R.id.hapinessProp);
        happiness.setText(String.valueOf(characteristicModel.getHappiness()));

        //Set Name
        name = findViewById(R.id.nameCaracterProp);
        name.setText(String.valueOf(characteristicModel.getName()));

        //Set Height
        height = findViewById(R.id.heightCaracterProp);
        height.setText(String.valueOf(characteristicModel.getHeight()));

        //Set Weight
        weight = findViewById(R.id.weightCaracterProp);
        weight.setText(String.valueOf(characteristicModel.getWeight()));

        //Set DaysAlive
        daysAlive = findViewById(R.id.daysAliveProp);
        daysAlive.setText(String.valueOf(characteristicModel.getDaysAlive()));

        //Set Health
        health = findViewById(R.id.healthProp);
        health.setText(String.valueOf(characteristicModel.getHealth()));

        //Set Coins
        coins = findViewById(R.id.coinsCaracterProp);
        coins.setText(String.valueOf(characteristicModel.getMoney()));

    }
}