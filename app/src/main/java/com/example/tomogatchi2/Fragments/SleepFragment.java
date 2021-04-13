package com.example.tomogatchi2.Fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tomogatchi2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SleepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SleepFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private Integer sleepCounter;

    private TextView sleepCondition;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    public static SleepFragment newInstance() {
        SleepFragment fragment = new SleepFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        sleepCounter = 0;
        sleepCondition = (TextView) getActivity().findViewById(R.id.sleepTextView);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
        else{
            sleepCondition.setText("Light sensor is not working properly");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (sensor != null) {
            sensorManager.registerListener(this, sensor,
                    Sensor.TYPE_LIGHT);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor == sensor){
            if( (int) event.values[0] < 100){
                sleepCondition.setText("Sleeping");
                sleepCounter++;
            }else{
                sleepCondition.setText("Too much light");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume(){
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
            sensorManager.unregisterListener(this, sensor);
        }
    }

    public Integer getSleepCounter(){ return this.sleepCounter; }

    public void resetCounter() { this.sleepCounter = 0; }


}