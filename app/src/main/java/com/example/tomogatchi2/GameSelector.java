package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selector);
    }

    public void ChangeToGame1(View view)
    {
        Intent intent = new Intent(this, Game1.class);
        startActivity(intent);
    }

    public void WIPgame(View view)
    {
        /*Intent intent = new Intent(this, Game2.class);
        startActivity(intent);*/
    }
}