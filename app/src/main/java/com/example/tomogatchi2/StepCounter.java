package com.example.tomogatchi2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import static androidx.core.content.ContextCompat.getSystemService;

public class StepCounter extends Fragment implements SensorEventListener {

    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";
    private TextView stepCounter;
    private TextView stepDetector;
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean counterSensor;
    int stepCount = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.step_counter_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        stepCounter = getActivity().findViewById(R.id.textView5);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            counterSensor = true;
        }
        else{
            stepCounter.setText("Counter sensor is not working properly");
            counterSensor = false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (sensor != null) {
            sensorManager.registerListener(this, sensor,
                    Sensor.TYPE_STEP_COUNTER);
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
            stepCount = (int) event.values[0];
            stepCounter.setText(String.valueOf(stepCount));
            return;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume(){
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            sensorManager.unregisterListener(this, sensor);
        }
    }
}


