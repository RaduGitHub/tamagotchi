package com.example.tomogatchi2;

import android.app.Application;
import android.content.Context;

public class MyContext extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyContext.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyContext.context;
    }

}
