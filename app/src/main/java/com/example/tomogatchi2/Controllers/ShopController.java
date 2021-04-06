package com.example.tomogatchi2.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.Utils.MyContext;

public class ShopController {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public ShopController(){
        this.context = MyContext.getAppContext();
        sharedPreferences = this.context.getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    //return String.valueOf(sharedPreferences.getInt(Data.BandagePrice, 0));
    public String getPizzaPrice(){
        return String.valueOf(sharedPreferences.getInt(Data.PizzaPrice, 0));
    }

    public String getPizzaOwned(){
        return String.valueOf(sharedPreferences.getInt(Data.Pizza, 0));
    }

    public String getIcePrice(){
        return String.valueOf(sharedPreferences.getInt(Data.IceCreamPrice, 0));
    }

    public String getIceOwned(){
        return String.valueOf(sharedPreferences.getInt(Data.IceCream, 0));
    }

    public String getFriesPrice(){
        return String.valueOf(sharedPreferences.getInt(Data.FriesPrice, 0));
    }

    public String getFriesOwned(){
        return String.valueOf(sharedPreferences.getInt(Data.Fries, 0));
    }

    public String getCookiePrice(){
        return String.valueOf(sharedPreferences.getInt(Data.CookiePrice, 0));
    }

    public String getCookieOwned(){
        return String.valueOf(sharedPreferences.getInt(Data.Cookie, 0));
    }

    public String getSodaPrice(){
        return String.valueOf(sharedPreferences.getInt(Data.SodaPrice, 0));
    }

    public String getSodaOwned(){
        return String.valueOf(sharedPreferences.getInt(Data.Soda, 0));
    }

    public String getHotDogPrice(){
        return String.valueOf(sharedPreferences.getInt(Data.HotDogPrice, 0));
    }

    public String getHotDogOwned(){
        return String.valueOf(sharedPreferences.getInt(Data.HotDog, 0));
    }
}
