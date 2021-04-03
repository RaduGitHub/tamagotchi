package com.example.tomogatchi2.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tomogatchi2.Controllers.ChoSaysController;
import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.R;

import java.util.Random;

import static com.example.tomogatchi2.Models.Data.MyPREFERENCES;

public class ChoSaysGameView extends AppCompatActivity {

    public String colors[] = {"Yellow", "Red", "Green", "Blue"};

    SharedPreferences sharedPreferences;
    //SharedPreferences.Editor editor;

    TextView score, timer, color;
    Button startButton;
    Button redButton, yelButton, bluButton, greButton;
    Random rand;

    ChoSaysController choSaysController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        score = findViewById(R.id.scorePetSays);
        timer = findViewById(R.id.counterPetSays);
        color = findViewById(R.id.colorPetSays);
        startButton = findViewById(R.id.startPetSays);
        redButton = findViewById(R.id.redButton);
        yelButton = findViewById(R.id.yellowButton);
        bluButton = findViewById(R.id.blueButton);
        greButton = findViewById(R.id.greenButton);

        redButton.setEnabled(false);
        greButton.setEnabled(false);
        bluButton.setEnabled(false);
        yelButton.setEnabled(false);

        choSaysController = new ChoSaysController();

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //editor = sharedPreferences.edit();

        rand = new Random();
    }

    public void redPress(View view){
        if(color.getText().toString().equals("Red")){
            score.setText(String.valueOf(Integer.parseInt(score.getText().toString()) + 1));
            color.setText((colors[rand.nextInt(4)]));
        }else{
            color.setText((colors[rand.nextInt(4)]));
        }
    }

    public void yellowPress(View view){
        if(color.getText().toString().equals("Yellow")){
            score.setText(String.valueOf(Integer.parseInt(score.getText().toString()) + 1));
            color.setText((colors[rand.nextInt(4)]));
        }else{
            color.setText((colors[rand.nextInt(4)]));
        }
    }

    public void bluePress(View view){
        if(color.getText().toString().equals("Blue")){
            score.setText(String.valueOf(Integer.parseInt(score.getText().toString()) + 1));
            color.setText((colors[rand.nextInt(4)]));
        }else{
            color.setText((colors[rand.nextInt(4)]));
        }
    }

    public void greenPress(View view){
        if(color.getText().toString().equals("Green")){
            score.setText(String.valueOf(Integer.parseInt(score.getText().toString()) + 1));
            color.setText((colors[rand.nextInt(4)]));
        }else{
            color.setText((colors[rand.nextInt(4)]));
        }
    }

    public void startPetSays(View view){
        redButton.setEnabled(true);
        greButton.setEnabled(true);
        bluButton.setEnabled(true);
        yelButton.setEnabled(true);
        color.setText((colors[rand.nextInt(4)]));
        score.setText(String.valueOf(0));
        new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished){
                timer.setText(String.valueOf(millisUntilFinished/1000));
            }
            public void onFinish(){
                redButton.setEnabled(false);
                greButton.setEnabled(false);
                bluButton.setEnabled(false);
                yelButton.setEnabled(false);
                timer.setText(String.valueOf(0));
                /*if(Integer.parseInt(score.getText().toString()) > 30 ){
                    editor.putInt(Data.Happiness, sharedPreferences.getInt(Data.Happiness, 0) + 10);
                }else{
                    editor.putInt(Data.Happiness, sharedPreferences.getInt(Data.Happiness, 0) + 5);
                }*/
                choSaysController.AddGameHappines(sharedPreferences, Integer.parseInt(score.
                                getText().toString()));
            }
        }.start();
    }

}