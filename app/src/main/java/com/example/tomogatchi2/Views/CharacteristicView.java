package com.example.tomogatchi2.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tomogatchi2.Controllers.CharacteristicController;
import com.example.tomogatchi2.Models.CharacteristicModel;
import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.R;

import static com.example.tomogatchi2.Models.Data.MyPREFERENCES;

public class CharacteristicView extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView happiness, name, height, weight, daysAlive, health, coins;
    //CharacteristicModel characteristicModel;
    CharacteristicController characteristicController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characteristics);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //characteristicModel = new CharacteristicModel(sharedPreferences);
        characteristicController = new CharacteristicController();

        //Set Happiness level
        happiness = findViewById(R.id.hapinessProp);
        happiness.setText(String.valueOf(characteristicController.getHappiness()));

        //Set Name
        name = findViewById(R.id.nameCaracterProp);
        name.setText(String.valueOf(characteristicController.getName()));

        //Set Height
        height = findViewById(R.id.heightCaracterProp);
        height.setText(String.valueOf(characteristicController.getHeight()));

        //Set Weight
        weight = findViewById(R.id.weightCaracterProp);
        weight.setText(String.valueOf(characteristicController.getWeight()));

        //Set DaysAlive
        daysAlive = findViewById(R.id.daysAliveProp);
        daysAlive.setText(String.valueOf(characteristicController.getDaysAlive()));

        //Set Health
        health = findViewById(R.id.healthProp);
        health.setText(String.valueOf(characteristicController.getHealth()));

        //Set Coins
        coins = findViewById(R.id.coinsCaracterProp);
        coins.setText(String.valueOf(characteristicController.getMoney()));

    }
}