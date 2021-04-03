package com.example.tomogatchi2.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.Utils.MyContext;

public class StartingScreenController {

    public static final String Name = "nameKey";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public StartingScreenController()
    {
        this.context = MyContext.getAppContext();
        sharedPreferences = this.context.getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String isNamed()
    {
        return sharedPreferences.getString(Name, "nameKey");
    }


}
