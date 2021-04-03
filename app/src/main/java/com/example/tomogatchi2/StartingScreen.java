package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tomogatchi2.Views.NamePetView;

public class StartingScreen extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        Intent intent;

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String isNamed = sharedPreferences.getString(Name, "nameKey");

        if(isNamed.compareTo("nameKey") == 0){
            intent = new Intent(StartingScreen.this, NamePetView.class);
        }
        else
        {
            intent = new Intent(StartingScreen.this, MainActivity.class);
        }

        startActivity(intent);
        finish();
    }
}

