package com.example.tomogatchi2.Controllers;

import android.content.SharedPreferences;

import com.example.tomogatchi2.Models.Data;

public class ChoSaysController {

    public void AddGameHappines(SharedPreferences sharedPreferences, int score)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //int happiness = sharedPreferences.getInt(Data.Happiness, 0);
        if(score > 30){
            editor.putInt(Data.Happiness, sharedPreferences.getInt(Data.Happiness, 0) + 10);
        }else{
            editor.putInt(Data.Happiness, sharedPreferences.getInt(Data.Happiness, 0) + 5);
        }
        editor.commit();
    }

}
