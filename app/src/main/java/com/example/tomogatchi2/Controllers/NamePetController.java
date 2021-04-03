package com.example.tomogatchi2.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.Utils.MyContext;

import java.util.Random;

public class NamePetController {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public NamePetController()
    {
        this.context = MyContext.getAppContext();
        sharedPreferences = this.context.getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        populateSharedPreferences(editor);
    }

    public void namePet(String name)
    {
        editor.putString(Data.Name, name);
        editor.commit();
    }

    protected void populateSharedPreferences(SharedPreferences.Editor editor)
    {
        Data.fragments = 0;
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
        editor.putLong(Data.DayBorn, System.currentTimeMillis());
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

        //Initialise price for items
        editor.putInt(Data.PizzaHappy, 1);
        editor.putInt(Data.IceCreamHappy, 2);
        editor.putInt(Data.FriesHappy, 5);
        editor.putInt(Data.CookieHappy, 7);
        editor.putInt(Data.SodaHappy, 10);
        editor.putInt(Data.HotDogHappy, 12);
        editor.putInt(Data.BandageHappy, 5);
        editor.putInt(Data.PillsHappy, 10);
        editor.commit();

        //Initialise player money
        editor.putInt(Data.Money, 500);
        editor.commit();

    }

}
