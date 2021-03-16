package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int x = 0;
    public static final String SHARED_PREFS = "sharedPref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IncreaseCoin(View view){
        TextView t = (TextView)findViewById(R.id.textView3);
        x = x + 1;
        t.setText(String.valueOf(x));
    }

    public void ChangeToShop(View view){
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }

    public void ChangeToDrugstore(View view){
        Intent intent = new Intent(this, Drugstore.class);
        startActivity(intent);
    }
}