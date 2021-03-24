package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import static com.example.tomogatchi2.Data.MyPREFERENCES;
import static com.example.tomogatchi2.Data.Name;

public class MainActivity extends AppCompatActivity {
    //public static final String MyPREFERENCES = "MyPrefs" ;
    //public static final String Name = "nameKey";
    TextView Money;
    SharedPreferences sharedPreferences;
    Context context;

    StepCounter fragment;

    Handler eventHandler = new Handler();

    Random randomTime = new Random();
    Random randomEvent = new Random();

    int foodCounter, sleepCounter, walkCounter, sickCounter, cleanCounter;

    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Data.DaysAlive, (int)(System.currentTimeMillis() - sharedPreferences.getLong(Data.DayBorn, 0)) / 86400000);

        fragment = new StepCounter();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        addFragment(fragment);


        eventHandler.postDelayed(eventController, 0);

    }

    //Shows the money updates when coming back to the screen
    @Override
    protected void onResume(){
        super.onResume();
        Money = (TextView)findViewById(R.id.textView3);
        Money.setText(String.valueOf(sharedPreferences.getInt(Data.Money, 0)));
    }

    public void IncreaseCoin(View view){

        Money.setText(String.valueOf(Integer.parseInt(Money.getText().toString()) + 1));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Data.Money, Integer.parseInt(Money.getText().toString()));
        editor.commit();

/*        long startTime = System.currentTimeMillis();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);

        System.out.println(formatter.format(calendar.getTime()));*/

        i++;
        if(i%5 == 0)
        {
            walkButton();
        }
    }

    public void ChangeToShop(View view){
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }

    public void ChangeToDrugstore(View view){
        Intent intent = new Intent(this, Drugstore.class);
        startActivity(intent);
    }

    public void ChangeToCharacteristics(View view)
    {
        Intent intent = new Intent(this, Characteristics.class);
        startActivity(intent);
    }

    public void ChangeToGameSelector(View view)
    {
        Intent intent = new Intent(this, GameSelector.class);
        startActivity(intent);
    }

    public void addFragment(Fragment fragment)
    {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container_view0, fragment).addToBackStack(null).show(fragment);
        fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();
    }

    public void walkButton()
    {
        ImageButton bt = findViewById(R.id.imageButton);
        bt.setVisibility(View.VISIBLE);
        bt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.show(fragment);
                Data.fragments += 1;
                fragmentTransaction.commit();
            }
        });
    }

    public void sleepButton()
    {
        ImageButton bt = findViewById(R.id.imageButton3);
        bt.setVisibility(View.VISIBLE);
        bt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // to do //
            }
        });
    }

    public void sickButton()
    {
        ImageButton bt = findViewById(R.id.imageButton0);
        bt.setVisibility(View.VISIBLE);
        bt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // to do //
            }
        });
    }

    public void cleanButton()
    {
        ImageButton bt = findViewById(R.id.imageButton4);
        bt.setVisibility(View.VISIBLE);
        bt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // to do //
            }
        });
    }

    public void foodButton()
    {
        ImageButton bt = findViewById(R.id.imageButton5);
        bt.setVisibility(View.VISIBLE);
        bt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // to do //
            }
        });
    }


    public void stepsDialog(String current, String total) {
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle(current + "/" + total);
        dialog.show();
    }

    public void hungryEventStart() {
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Your pet is hungry, give it some food");
        dialog.show();
    }

    public void happinessController()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int happiness = sharedPreferences.getInt(Data.Happiness, 0);
        Log.d("HAPPENED", "happy " + happiness);
        if(happiness > 1)
        {
            happiness  = happiness - 1;
            editor.putInt(Data.Happiness, happiness);
        }
        editor.commit();
    }

    private Runnable eventController = new Runnable()
    {
        public void run()
        {
            incrementEventTimers();
            EventsCheck();
            happinessController();
            i++;

            eventHandler.postDelayed(this, 1000);
        }
    };

    public void incrementEventTimers()
    {
        if(Data.sleepActive == false)
        {
            this.sleepCounter++;
        }

        if(Data.foodActive == false)
        {
            this.foodCounter++;
        }

        if(Data.stepsActive == false)
        {
            this.walkCounter++;
        }

        if(Data.sickActive == false)
        {
            this.sickCounter++;
        }

        if(Data.cleanActive == false)
        {
            this.cleanCounter++;
        }

    }

    public void EventsCheck()
    {
        FoodEventCheck();
        WalkEventCheck();
        SickEventCheck();
        SleepEventCheck();
        CleanEventCheck();
    }


    public void FoodEventCheck()
    {
        if(!Data.foodActive)
        {
            int time = randomTime.nextInt(100);
            if(foodCounter >= 4 && foodCounter < 6 && time <= 10)
            {
                Log.d("MANCARE ", " random = " + i);
                Data.foodActive = true;
                foodCounter = 0;
                foodButton();
            }
            else if(foodCounter >= 6 && foodCounter < 8 && time <= 25)
            {
                Log.d("MANCARE ", " random = " + i);
                Data.foodActive = true;
                foodCounter = 0;
                foodButton();
            }
            else if(foodCounter >= 8 && foodCounter < 10 && time <= 50)
            {
                Log.d("MANCARE ", " random = " + i);
                Data.foodActive = true;
                foodCounter = 0;
                foodButton();
            }
            else if(foodCounter == 10)
            {
                Log.d("MANCARE ", " random = " + i);
                Data.foodActive = true;
                foodCounter = 0;
                foodButton();
            }
        }
        else if(Data.foodActive && Data.feeding)
        {
            ImageButton bt = findViewById(R.id.imageButton5);
            bt.setVisibility(View.INVISIBLE);
            Data.foodActive = false;
            Data.feeding = false;

        }

    }

    public void WalkEventCheck()
    {
        if(!Data.stepsActive){
            int time = randomTime.nextInt(100);
            if(walkCounter >= 6 && walkCounter < 8 && time <= 10)
            {
                Log.d("PLIMBARE ", " random = " + i);
                walkCounter = 0;
                Data.stepsActive = true;
                walkButton();
            }
            else if(walkCounter >= 8 && walkCounter < 10 && time <= 25)
            {
                Log.d("PLIMBARE ", " random = " + i);
                walkCounter = 0;
                Data.stepsActive = true;
                walkButton();
            }
            else if(walkCounter >= 10 && walkCounter < 14 && time <= 50)
            {
                Log.d("PLIMBARE ", " random = " + i);
                walkCounter = 0;
                Data.stepsActive = true;
                walkButton();
            }
            else if(walkCounter == 14)
            {
                Log.d("PLIMBARE ", " random = " + i);
                walkCounter = 0;
                Data.stepsActive = true;
                walkButton();
            }
        }
    }

    public void SickEventCheck() {
        if (!Data.sickActive)
        {
            int time = randomTime.nextInt(100);



            if(sickCounter >= 10)
            {
                Data.sickActive = true;
                sickCounter = 0;
                sickButton();
            }

            if(sickCounter >= 720 && foodCounter < 2160 && time <= 10)
            {
                Log.d("SICK ", " random = " + i);
                Data.sickActive = true;
                sickCounter = 0;
                sickButton();
            }
            else if(sickCounter >= 2160 && sickCounter < 4320 && time <= 25)
            {
                Log.d("SICK ", " random = " + i);
                Data.sickActive = true;
                sickCounter = 0;
                sickButton();
            }
            else if(sickCounter >= 4320 && time <= 50)
            {
                Log.d("SICK ", " random = " + i);
                Data.sickActive = true;
                sickCounter = 0;
                sickButton();
            }
        }
        else if(Data.sickActive && Data.caring)
        {
            ImageButton bt = findViewById(R.id.imageButton0);
            bt.setVisibility(View.INVISIBLE);
            Data.sickActive = false;
            Data.caring = false;

        }
    }

    public void SleepEventCheck()
    {
        if(!Data.sleepActive)
        {
            int time = randomTime.nextInt(100);
            if(sleepCounter < 8 && time < 5)
            {
                Log.d("SOMN ", " random = " + i);
                Data.sleepActive = true;
                sleepCounter = 0;
                sleepButton();
            }
            else if(sleepCounter >= 8 && sleepCounter < 16 && time <= 25)
            {
                Log.d("SOMN ", " random = " + i);
                Data.sleepActive = true;
                sleepCounter = 0;
                sleepButton();
            }
            else if(sleepCounter >= 16 && sleepCounter < 24 && time <= 50)
            {
                Log.d("SOMN ", " random = " + i);
                Data.sleepActive = true;
                sleepCounter = 0;
                sleepButton();
            }
            else if(sleepCounter >= 24)
            {
                Log.d("SOMN ", " random = " + i);
                Data.sleepActive = true;
                sleepCounter = 0;
                sleepButton();
            }
        }
    }

    public void CleanEventCheck() {
        if (!Data.cleanActive) {
            int time = randomTime.nextInt(100);
            if (cleanCounter < 24 && time < 10) {
                Log.d("Clean ", " random = " + i);
                Data.cleanActive = true;
                cleanCounter = 0;
                cleanButton();
            } else if (cleanCounter >= 24 && cleanCounter < 48 && time <= 25) {
                Log.d("Clean ", " random = " + i);
                Data.cleanActive = true;
                cleanCounter = 0;
                cleanButton();
            } else if (cleanCounter >= 48 && cleanCounter < 96 && time <= 50) {
                Log.d("Clean ", " random = " + i);
                Data.cleanActive = true;
                cleanCounter = 0;
                cleanButton();
            } else if (cleanCounter >= 96) {
                Log.d("Clean ", " random = " + i);
                Data.cleanActive = true;
                cleanCounter = 0;
                cleanButton();
            }
        }
    }
}