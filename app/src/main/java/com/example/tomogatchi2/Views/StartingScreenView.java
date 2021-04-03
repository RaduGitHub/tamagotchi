package com.example.tomogatchi2.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tomogatchi2.Controllers.StartingScreenController;
import com.example.tomogatchi2.MainActivity;
import com.example.tomogatchi2.R;

public class StartingScreenView extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    StartingScreenController startingScreenController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        startingScreenController = new StartingScreenController();
        Intent intent;

        String isNamed = startingScreenController.isNamed();

        if(isNamed.compareTo("nameKey") == 0){
            intent = new Intent(StartingScreenView.this, NamePetView.class);
        }
        else
        {
            intent = new Intent(StartingScreenView.this, MainActivity.class);
        }

        startActivity(intent);
        finish();
    }
}

