package com.example.tomogatchi2.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.tomogatchi2.Fragments.SleepFragment;
import com.example.tomogatchi2.Fragments.StepCounterFragment;
import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.Services.BackgroundService;
import com.example.tomogatchi2.Utils.MyContext;

import static com.example.tomogatchi2.Models.Data.MyPREFERENCES;

public class MainActivityController {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public MainActivityController(){
        this.context = MyContext.getAppContext();
        sharedPreferences = this.context.getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void TimeAlive()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Data.DaysAlive, (int)(System.currentTimeMillis() - sharedPreferences.getLong(Data.DayBorn, 0)) / 86400000);
        editor.commit();
    }

    public String GetMoney()
    {
        return String.valueOf(sharedPreferences.getInt(Data.Money, 0));
    }

    public void SetMoney(String Money)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Data.Money, Integer.parseInt(Money));
        editor.commit();
    }

    public int GetHappines()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getInt(Data.Happiness, 0);
    }

    public void IncreaseHappiness(int happiness)
    {
        editor.putInt(Data.Happiness, happiness);
        editor.commit();
    }

    public void incrementEventTimers()
    {
        if(Data.sleepActive == false)
        {
            Data.sleepCounter++;
        }

        if(Data.foodActive == false)
        {
            Data.foodCounter++;
        }

        if(Data.stepsActive == false)
        {
            Data.walkCounter++;
        }

        if(Data.sickActive == false)
        {
            Data.sickCounter++;
        }

        if(Data.cleanActive == false)
        {
            Data.cleanCounter++;
        }

    }

}
