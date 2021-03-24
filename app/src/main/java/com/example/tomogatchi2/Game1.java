package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game1 extends AppCompatActivity {

    TextView timerTextView;
    TextView score;
    ImageButton imageButton;
    long startTime = 0;
    int points;
    Activity thisActivity;
    SharedPreferences sharedPreferences;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;


            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);

            if(seconds == 30)
            {
                final Timer timer = new Timer();
                //imageButton.animate().x(100).y(100).setDuration(0).start();
                timerHandler.removeCallbacks(timerRunnable);

                AlertDialog alertDialog = new AlertDialog.Builder(thisActivity)
                        .setTitle("Game finished")
                        .setMessage("You obtained " + points + " points")
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(thisActivity, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();

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
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = this;
        setContentView(R.layout.activity_game1);
        score = (TextView) findViewById(R.id.textView2);
        //get display size.
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;
        sharedPreferences = getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);

        AlertDialog alertDialog = new AlertDialog.Builder(thisActivity)
                .setTitle("Tutorial")
                .setMessage("Catch as many cho's as possible in 30 seconds")
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        startTime = System.currentTimeMillis();
                        timerHandler.postDelayed(timerRunnable, 0);
                        timerTextView.setVisibility(View.VISIBLE);

                    }
                })
                .show();



        timerTextView = findViewById(R.id.textView7);
        timerTextView.setVisibility(View.INVISIBLE);
        ImageButton button = (ImageButton) findViewById(R.id.imageButton2);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                points += 1;
                score.setText(String.valueOf(points));
                final int randomX = new Random().nextInt(width - 200);
                final int randomY = new Random().nextInt(height - 300);

                button.animate().x(randomX).y(randomY);
            }
        });




    }


}