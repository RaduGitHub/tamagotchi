package com.example.tomogatchi2.Controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;

import com.example.tomogatchi2.Data;
import com.example.tomogatchi2.MainActivity;

import java.util.Random;
import java.util.Timer;

public class CatchChoController {


    public void AddGameHappines(SharedPreferences sharedPreferences, int points)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int happiness = sharedPreferences.getInt(Data.Happiness, 0);
        if(happiness < 100)
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
