package com.example.tomogatchi2.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.Utils.MyContext;

public class RecyclerViewController {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public RecyclerViewController()
    {
        this.context = MyContext.getAppContext();
        sharedPreferences = this.context.getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);

    }

    public void setOwned(String name, String owned)
    {
        editor = sharedPreferences.edit();
        editor.putInt(name + "Key", Integer.parseInt(owned));
        editor.commit();
    }

    public void addHappiness(String name)
    {
        editor = sharedPreferences.edit();
        int happiness = sharedPreferences.getInt(Data.Happiness, 0);
        int happinessToAdd = sharedPreferences.getInt(name + "HappyKey", 0);
        Log.d("xxx", "onClick: " + happinessToAdd);
        if(happiness < 100)
        {
            happiness  = happiness + happinessToAdd;
            editor.putInt(Data.Happiness, happiness);
        }
        editor.commit();
    }

    public int getMoney()
    {
        editor = sharedPreferences.edit();
        return sharedPreferences.getInt(Data.Money, 0);
    }

    public void addItem(String name, String owned)
    {
        editor.putInt(name + "Key", Integer.parseInt(owned));
        editor.commit();
    }

    public void deductMoney(int currentMoney, String price)
    {
        editor.putInt(Data.Money, currentMoney - Integer.parseInt(price));
        editor.commit();
    }

}
