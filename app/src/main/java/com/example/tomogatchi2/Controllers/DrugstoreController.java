package com.example.tomogatchi2.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.Utils.MyContext;

public class DrugstoreController {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public DrugstoreController()
    {
        this.context = MyContext.getAppContext();
        sharedPreferences = this.context.getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getBandagePrice()
    {
        return String.valueOf(sharedPreferences.getInt(Data.BandagePrice, 0));
    }

    public String getPillsPrice()
    {
        return String.valueOf(sharedPreferences.getInt(Data.PillsPrice, 0));
    }

    public String getBandageOwned()
    {
        return String.valueOf(sharedPreferences.getInt(Data.Bandage, 0));
    }

    public String getPillsOwned()
    {
        return String.valueOf(sharedPreferences.getInt(Data.Pills, 0));
    }

}
