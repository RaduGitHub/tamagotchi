package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.tomogatchi2.Data.MyPREFERENCES;
import static com.example.tomogatchi2.Data.Name;

public class MainActivity extends AppCompatActivity {
    int x = 0;
    //public static final String MyPREFERENCES = "MyPrefs" ;
    //public static final String Name = "nameKey";

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String petName = sharedPreferences.getString(Name, "nameKey");
        TextView t = (TextView)findViewById(R.id.textView2);
        t.setText(petName);
        //Set money on mainscreen
        TextView Money = (TextView)findViewById(R.id.textView3);
        Money.setText(String.valueOf(sharedPreferences.getInt(Data.Money, 0)));
    }

    //Shows the money updates when coming back to the screen
    @Override
    protected void onResume(){
        super.onResume();
        TextView Money = (TextView)findViewById(R.id.textView3);
        Money.setText(String.valueOf(sharedPreferences.getInt(Data.Money, 0)));

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