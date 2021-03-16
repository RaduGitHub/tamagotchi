package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class StartingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        Intent intent;

        if(isNamed){
            intent = new Intent(StartingScreen.this, MainActivity.class);
        }
        else
        {
            intent = new Intent(StartingScreen.this, NamePet.class);
        }

        startActivity(intent);
        finish();
    }
}