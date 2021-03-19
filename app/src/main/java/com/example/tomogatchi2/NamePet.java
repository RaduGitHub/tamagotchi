package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class NamePet extends AppCompatActivity {

    Button b1;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_pet);
        b1=(Button)findViewById(R.id.submitButton);

        sharedPreferences = getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);

        EditText t = (EditText)findViewById(R.id.namingText);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Data.Name, t.getText().toString());
                populateSharedPreferences(editor);
                editor.commit();
                Intent intent;
                intent = new Intent(NamePet.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    protected void populateSharedPreferences(SharedPreferences.Editor editor)
    {
        //Total items for foodstore and drugstore
        editor.putInt("FoodstoreTotalItemsKey", 6);
        editor.putInt("DrugstoreTotalItemsKey", 2);
        editor.commit();

        //Initialise pet caracteristics
        editor.putInt(Data.Weight, new Random().nextInt(61) + 40);
        editor.putInt(Data.Height, new Random().nextInt(101) + 100);
        editor.putInt(Data.DaysAlive, 0);
        editor.putInt(Data.Happiness, 50);
        editor.putInt(Data.Health, 100);
        editor.commit();

        //Initialise owned items
        editor.putInt(Data.Pizza, 0);
        editor.putInt(Data.IceCream, 0);
        editor.putInt(Data.Fries, 0);
        editor.putInt(Data.Cookie, 0);
        editor.putInt(Data.Soda, 0);
        editor.putInt(Data.HotDog, 0);
        editor.putInt(Data.Bandage, 0);
        editor.putInt(Data.Pills, 0);
        editor.commit();

        //Initialise price for items
        editor.putInt(Data.PizzaPrice, 5);
        editor.putInt(Data.IceCreamPrice, 10);
        editor.putInt(Data.FriesPrice, 20);
        editor.putInt(Data.CookiePrice, 50);
        editor.putInt(Data.SodaPrice, 100);
        editor.putInt(Data.HotDogPrice, 250);
        editor.putInt(Data.BandagePrice, 50);
        editor.putInt(Data.PillsPrice, 150);
        editor.commit();

        //Initialise player money
        editor.putInt(Data.Money, 500);
        editor.commit();

    }
}