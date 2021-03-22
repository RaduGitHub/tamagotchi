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

import org.w3c.dom.Text;

import static androidx.core.content.ContextCompat.getSystemService;

public class StepCounter extends DialogFragment implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView textView;
    boolean activityRunning;
    private TextView count;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.step_counter_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        count = (TextView) getActivity().findViewById(R.id.textView5);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

    }

    @Override
    public void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Log.d("Sensor", "onResume: Not available");
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        activityRunning = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (activityRunning) {
            int stepCounter = (int) event.values[0];
            count.setText(String.valueOf(stepCounter));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}


