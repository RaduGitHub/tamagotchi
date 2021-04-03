package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.app.ActivityManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tomogatchi2.Fragments.SleepFragment;
import com.example.tomogatchi2.Fragments.StepCounterFragment;
import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.Services.BackgroundService;
import com.example.tomogatchi2.Utils.NotificationReceiver;
import com.example.tomogatchi2.Views.DrugstoreView;
import com.example.tomogatchi2.Views.GameSelectorView;
import com.example.tomogatchi2.Views.Shop;

import java.util.Random;

import static com.example.tomogatchi2.Models.Data.MyPREFERENCES;

public class MainActivity extends AppCompatActivity {
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";
    private NotificationReceiver mReceiver;
    //public static final String MyPREFERENCES = "MyPrefs" ;
    //public static final String Name = "nameKey";
    TextView Money;
    SharedPreferences sharedPreferences;
    Context context;

    StepCounterFragment fragment;
    SleepFragment sleepFragment;

    Handler eventHandler = new Handler();

    Random randomTime = new Random();
    Random randomEvent = new Random();

    boolean cleaning = false;
    int cleaningCounterStart;
    int current = 0;
    private NotificationManager mNotifyManager;

    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Data.DaysAlive, (int)(System.currentTimeMillis() - sharedPreferences.getLong(Data.DayBorn, 0)) / 86400000);
        editor.commit();
        fragment = new StepCounterFragment();
        sleepFragment = new SleepFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        addFragment(fragment);
        addFragment(sleepFragment);

        eventHandler.postDelayed(eventController, 0);
        //createNotificationChannel();
        //mReceiver = new NotificationReceiver();
        //registerReceiver(mReceiver,new IntentFilter(ACTION_UPDATE_NOTIFICATION));
        BackgroundService service = new BackgroundService();
        Intent serviceIntent = new Intent(this, BackgroundService.class);
        if(!isMyServiceRunning(service.getClass()))
        {
            startService(serviceIntent);
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i ("Service status", "Running");
                return true;
            }
        }
        Log.i ("Service status", "Not running");
        return false;
    }

    //Shows the money updates when coming back to the screen
    @Override
    protected void onResume(){
        super.onResume();
        Money = (TextView)findViewById(R.id.textView3);
        Money.setText(String.valueOf(sharedPreferences.getInt(Data.Money, 0)));
    }

    public void IncreaseCoin(View view){


        if(cleaning == true)
        {
            current++;

            if(current == 10)
            {
                ImageButton bt = findViewById(R.id.imageButton4);
                bt.setVisibility(View.INVISIBLE);

                final Dialog dialog = new Dialog(this); // Context, this, etc.
                dialog.setContentView(R.layout.dialog4);
                dialog.show();
                cleaning = false;
                current = 0;
            }
        }
        else
        {
            Money.setText(String.valueOf(Integer.parseInt(Money.getText().toString()) + 1));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(Data.Money, Integer.parseInt(Money.getText().toString()));
            editor.commit();
        }

    }

    public void ChangeToShop(View view){
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }

    public void ChangeToDrugstore(View view){
        Intent intent = new Intent(this, DrugstoreView.class);
        startActivity(intent);
    }

    public void ChangeToCharacteristics(View view)
    {
        Intent intent = new Intent(this, Characteristics.class);
        startActivity(intent);
    }

    public void ChangeToGameSelector(View view)
    {
        Intent intent = new Intent(this, GameSelectorView.class);
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
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.show(sleepFragment);
                fragmentTransaction.commit();
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
                cleanDialog();

                cleaning = true;
            }
        });

    }

    public void cleanDialog()
    {
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.dialog5);
        dialog.show();
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
            //happinessController();
            i++;

            eventHandler.postDelayed(this, Data.tick);
        }
    };

    public void incrementEventTimers()
    {
        if(Data.sleepActive == false)
        {
            Data.sleepCounter++;
        }

        if(Data.foodActive == false)
        {
            Data.foodCounter++;
        }

        if(Data.stepsActive == false)
        {
            Data.walkCounter++;
        }

        if(Data.sickActive == false)
        {
            Data.sickCounter++;
        }

        if(Data.cleanActive == false)
        {
            Data.cleanCounter++;
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

        if(Data.foodActive && Data.feeding)
        {
            ImageButton bt = findViewById(R.id.imageButton5);
            bt.setVisibility(View.INVISIBLE);
            Data.foodActive = false;
            Data.feeding = false;

        }
        else if(Data.foodActive)
        {
            foodButton();
        }

    }

    public void WalkEventCheck()
    {
        if(Data.stepsActive)
        {
            walkButton();
        }
    }

    public void SickEventCheck() {
        if (Data.sickActive)
        {
            sickButton();
        }
        if(Data.sickActive && Data.caring)
        {
            ImageButton bt = findViewById(R.id.imageButton0);
            bt.setVisibility(View.INVISIBLE);
            Data.sickActive = false;
            Data.caring = false;

        }
    }

    public void SleepEventCheck()
    {
        if(Data.sleepActive)
        {
            sleepButton();
        }
    }

    public void CleanEventCheck() {
        if (Data.cleanActive)
        {
            cleanButton();
        }
    }
}