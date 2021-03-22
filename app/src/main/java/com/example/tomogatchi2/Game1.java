package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game1 extends AppCompatActivity {

    TextView timerTextView;
    ImageButton imageButton;
    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {


            /*
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;


            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);

            if(seconds == 5)
            {
                final Timer timer = new Timer();
                imageButton.animate().x(100).y(100).setDuration(0).start();
                timerHandler.removeCallbacks(timerRunnable);
            } */
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        //get display size.
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;

        //
        Random n = new Random();
        final int height01 = n.nextInt(height - 0) + 0;
        Random m = new Random();
        final int width01 = m.nextInt(width - 0) + 0;

        ImageButton button = (ImageButton) findViewById(R.id.imageButton2);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final int randomX = new Random().nextInt(width - 50);
                final int randomY = new Random().nextInt(height - 50);

                button.animate().x(randomX).y(randomY);

                //button.animate().translateX(100);
                //animates button movement.
                /*
                TranslateAnimation animation01 = new TranslateAnimation(0, 100, 0, 300);
                animation01.setDuration(500);
                animation01.setAnimationListener(new animationListener());
                button.startAnimation(animation01); */
            }

            class animationListener implements Animation.AnimationListener {

                @Override
                public void onAnimationStart(Animation animation) {
                }
                @Override
                public void onAnimationEnd(Animation animation) {

                    //button.animate().x(50f).y(100f);

                    //button.animate().translationX(100);

                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            }
        });

        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        timerTextView = (TextView) findViewById(R.id.textView2);
        imageButton = (ImageButton) findViewById(R.id.imageButton0);


        Button b = (Button) findViewById(R.id.button3);
        b.setText("start");
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;

                startTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);
                b.setText("stop");
            }
        }); */




    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.button);
        b.setText("start");
    }


}