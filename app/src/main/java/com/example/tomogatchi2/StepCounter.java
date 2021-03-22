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


    private TextView stepCounter;
    private TextView stepDetector;
    private SensorManager sensorManager;
    private Sensor sensor;
    private View view;

    boolean running = false;
    float totalSteps = 0f;
    Sensor stepSensor;
    boolean sensorPresent;
    TextView text;

    public StepCounter()
    {
        //empty
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.step_counter_fragment, container, false);
        Context context = MyContext.getAppContext();

        onClick();
        text = view.findViewById(R.id.textView5);
        return view;
    }

    public static StepCounter newInstance()
    {
        return new StepCounter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //stepCounter = view.findViewById(R.id.textView5);

        super.onCreate(savedInstanceState);
        Context context = MyContext.getAppContext();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            sensorPresent = true;
        }
        else
        {
            sensorPresent = false;
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
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor == stepSensor)
        {
            totalSteps = (int)sensorEvent.values[0];
            text.setText(String.valueOf(totalSteps));
        }

    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.unregisterListener(this, stepSensor);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void onClick()
    {

    }

}
