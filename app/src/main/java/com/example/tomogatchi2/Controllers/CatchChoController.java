package com.example.tomogatchi2.Controllers;

import android.content.SharedPreferences;

import com.example.tomogatchi2.Models.Data;

public class CatchChoController {


    public void AddGameHappines(SharedPreferences sharedPreferences, int points)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int happiness = sharedPreferences.getInt(Data.Happiness, 0);
        if(happiness <= 90)
        {
            happiness  = happiness + 10;
            editor.putInt(Data.Happiness, happiness);
        }
        int coins = sharedPreferences.getInt(Data.Money, 0);
        coins = coins + ((int) Math.floor(happiness / 20)) * points;
        editor.putInt(Data.Money, coins);
        editor.commit();
    }


}
