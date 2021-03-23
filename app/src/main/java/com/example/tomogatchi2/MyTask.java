package com.example.tomogatchi2;

import java.util.TimerTask;

public class MyTask extends TimerTask {

    String name;
    MyTask(String n) {
        name = n;
    }
    @Override
    public void run() {
        System.err.println(name);

    }

}
