package com.example.tomogatchi2.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tomogatchi2.R;

public class GameSelectorView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selector);
    }

    public void ChangeToGame1(View view)
    {
        Intent intent = new Intent(this, CatchChoView.class);
        startActivity(intent);
    }

    public void ChangeToGame2(View view)
    {
        Intent intent = new Intent(this, ChoSaysGameView.class);
        startActivity(intent);
    }
}