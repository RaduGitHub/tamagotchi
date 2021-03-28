package com.example.tomogatchi2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Random;

import static com.example.tomogatchi2.Data.MyPREFERENCES;

public class BackgroundService extends Service {
    private final LocalBinder mBinder = new LocalBinder();
    protected Handler eventHandler = new Handler();
    protected Toast mToast;
    SharedPreferences sharedPreferences;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final String channelName = "Background Service";
    private static final int NOTIFICATION_ID = 0;
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";
    private NotificationReceiver mReceiver;
    private NotificationManager mNotifyManager;
    int i = 0;
    Random randomTime = new Random();

    public class LocalBinder extends Binder {
        public BackgroundService getService() {
            return BackgroundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Log.d("service", "run: CREATE");
        createNotificationChannel();
        mReceiver = new NotificationReceiver();
        registerReceiver(mReceiver,new IntentFilter(ACTION_UPDATE_NOTIFICATION));

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());

    }

    public void startMyOwnForeground()
    {
        Notification notification = getNotificationBuilder("Tamagotchi is runing", "While this is active your pet will be updated").setOngoing(true)
                .build();
        startForeground(-1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, NotificationReceiver.class);
        this.sendBroadcast(broadcastIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        eventHandler.post(eventController);

        return android.app.Service.START_STICKY;
    }

    private Runnable eventController = new Runnable()
    {
        public void run()
        {
            incrementEventTimers();
            Log.d("service", "run: DA" + i);
            i++;
            FoodEventCheck();
            WalkEventCheck();
            SickEventCheck();
            SleepEventCheck();
            CleanEventCheck();

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

    public void createNotificationChannel()
    {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }


    }

    private NotificationCompat.Builder getNotificationBuilder(String title, String text)
    {
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_android);
        return notifyBuilder;
    }

    public void sendNotification(String title, String text, int id) {
        Intent updateIntent = new Intent(this, MainActivity.class);
        PendingIntent updatePendingIntent = PendingIntent.getActivity(this, 0, updateIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder(title, text);
        notifyBuilder.addAction(R.drawable.ic_android, "Open", updatePendingIntent);
        mNotifyManager.notify(id, notifyBuilder.build());
    }

    public void FoodEventCheck()
    {
        if(!Data.foodActive)
        {
            int time = randomTime.nextInt(100);
            if(Data.foodCounter >= 4 && Data.foodCounter < 6 && time <= 10)
            {
                Log.d("MANCARE ", " random = " + i);
                Data.foodActive = true;
                Data.foodCounter = 0;
                sendNotification("Your pet is hungry", "Feed him you monster!", 0);
            }
            else if(Data.foodCounter >= 6 && Data.foodCounter < 8 && time <= 25)
            {
                Log.d("MANCARE ", " random = " + i);
                Data.foodActive = true;
                Data.foodCounter = 0;
                sendNotification("Your pet is hungry", "Feed him you monster!", 0);
            }
            else if(Data.foodCounter >= 8 && Data.foodCounter < 10 && time <= 50)
            {
                Log.d("MANCARE ", " random = " + i);
                Data.foodActive = true;
                Data.foodCounter = 0;
                sendNotification("Your pet is hungry", "Feed him you monster!", 0);
            }
            else if(Data.foodCounter == 10)
            {
                Log.d("MANCARE ", " random = " + i);
                Data.foodActive = true;
                Data.foodCounter = 0;
                sendNotification("Your pet is hungry", "Feed him you monster!", 0);
            }
        }

    }

    public void WalkEventCheck()
    {
        if(!Data.stepsActive){
            int time = randomTime.nextInt(100);
            if(Data.walkCounter >= 6 && Data.walkCounter < 8 && time <= 10)
            {
                Log.d("PLIMBARE ", " random = " + i);
                Data.walkCounter = 0;
                Data.stepsActive = true;
                sendNotification("Your pet wants a walk!", "Go take him for a walk", 1);
            }
            else if(Data.walkCounter >= 8 && Data.walkCounter < 10 && time <= 25)
            {
                Log.d("PLIMBARE ", " random = " + i);
                Data.walkCounter = 0;
                Data.stepsActive = true;
                sendNotification("Your pet wants a walk!", "Go take him for a walk", 1);
            }
            else if(Data.walkCounter >= 10 && Data.walkCounter < 14 && time <= 50)
            {
                Log.d("PLIMBARE ", " random = " + i);
                Data.walkCounter = 0;
                Data.stepsActive = true;
                sendNotification("Your pet wants a walk!", "Go take him for a walk", 1);
            }
            else if(Data.walkCounter == 14)
            {
                Log.d("PLIMBARE ", " random = " + i);
                Data.walkCounter = 0;
                Data.stepsActive = true;
                sendNotification("Your pet wants a walk!", "Go take him for a walk", 1);
            }
        }
    }

    public void SickEventCheck() {
        if (!Data.sickActive)
        {
            int time = randomTime.nextInt(100);
            if(Data.sickCounter >= 10)
            {
                Data.sickActive = true;
                Data.sickCounter = 0;
                sendNotification("Your pet is sick!", "Go and take care of him so he won't feel bad anymore : (", 3);
            }

            if(Data.sickCounter >= 720 && Data.foodCounter < 2160 && time <= 10)
            {
                Log.d("SICK ", " random = " + i);
                Data.sickActive = true;
                Data.sickCounter = 0;
                sendNotification("Your pet is sick!", "Go and take care of him so he won't feel bad anymore : (", 3);
            }
            else if(Data.sickCounter >= 2160 && Data.sickCounter < 4320 && time <= 25)
            {
                Log.d("SICK ", " random = " + i);
                Data.sickActive = true;
                Data.sickCounter = 0;
                sendNotification("Your pet is sick!", "Go and take care of him so he won't feel bad anymore : (", 3);
            }
            else if(Data.sickCounter >= 4320 && time <= 50)
            {
                Log.d("SICK ", " random = " + i);
                Data.sickActive = true;
                Data.sickCounter = 0;
                sendNotification("Your pet is sick!", "Go and take care of him so he won't feel bad anymore : (", 3);
            }
        }
    }

    public void SleepEventCheck()
    {
        if(!Data.sleepActive)
        {
            int time = randomTime.nextInt(100);
            if(Data.sleepCounter < 8 && time < 5)
            {
                Log.d("SOMN ", " random = " + i);
                Data.sleepActive = true;
                Data.sleepCounter = 0;
                sendNotification("Your pet is tired!", "Go and put him to sleep so he can rest", 2);
            }
            else if(Data.sleepCounter >= 8 && Data.sleepCounter < 16 && time <= 25)
            {
                Log.d("SOMN ", " random = " + i);
                Data.sleepActive = true;
                Data.sleepCounter = 0;
                sendNotification("Your pet is tired!", "Go and put him to sleep so he can rest", 2);
            }
            else if(Data.sleepCounter >= 16 && Data.sleepCounter < 24 && time <= 50)
            {
                Log.d("SOMN ", " random = " + i);
                Data.sleepActive = true;
                Data.sleepCounter = 0;
                sendNotification("Your pet is tired!", "Go and put him to sleep so he can rest", 2);
            }
            else if(Data.sleepCounter >= 24)
            {
                Log.d("SOMN ", " random = " + i);
                Data.sleepActive = true;
                Data.sleepCounter = 0;
                sendNotification("Your pet is tired!", "Go and put him to sleep so he can rest", 2);
            }
        }
    }

    public void CleanEventCheck() {
        if (!Data.cleanActive) {
            int time = randomTime.nextInt(100);
            if (Data.cleanCounter < 24 && time < 10)
            {
                Log.d("Clean ", " random = " + i);
                Data.cleanActive = true;
                Data.cleanCounter = 0;
                sendNotification("Your pet is dirty!", "Go and give him a hand in cleaning himself", 4);
            } else if (Data.cleanCounter >= 24 && Data.cleanCounter < 48 && time <= 25)
            {
                Log.d("Clean ", " random = " + i);
                Data.cleanActive = true;
                Data.cleanCounter = 0;
                sendNotification("Your pet is dirty!", "Go and give him a hand in cleaning himself", 4);
            } else if (Data.cleanCounter >= 48 && Data.cleanCounter < 96 && time <= 50)
            {
                Log.d("Clean ", " random = " + i);
                Data.cleanActive = true;
                Data.cleanCounter = 0;
                sendNotification("Your pet is dirty!", "Go and give him a hand in cleaning himself", 4);
            } else if (Data.cleanCounter >= 96)
            {
                Log.d("Clean ", " random = " + i);
                Data.cleanActive = true;
                Data.cleanCounter = 0;
                sendNotification("Your pet is dirty!", "Go and give him a hand in cleaning himself", 4);
            }
        }
    }

}