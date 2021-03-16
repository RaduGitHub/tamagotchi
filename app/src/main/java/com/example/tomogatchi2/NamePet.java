package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NamePet extends AppCompatActivity {

    Button b1;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";

    public static final String Pizza = "PizzaKey";
    public static final String IceCream = "IceCreamKey";
    public static final String Fries = "FriesKey";
    public static final String Cookie = "CookieKey";
    public static final String Soda = "SodaKey";
    public static final String HotDog = "HotDogKey";
    public static final String Bandage = "bandageKey";
    public static final String Pills = "pillsKey";

    public static final String PizzaPrice = "PizzaPriceKey";
    public static final String IceCreamPrice = "IceCreamPriceKey";
    public static final String FriesPrice = "FriesPriceKey";
    public static final String CookiePrice = "CookiePriceKey";
    public static final String SodaPrice = "SodaPriceKey";
    public static final String HotDogPrice = "HotDogPriceKey";
    public static final String BandagePrice = "bandagePriceKey";
    public static final String PillsPrice = "pillsPriceKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_pet);
        b1=(Button)findViewById(R.id.submitButton);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        EditText t = (EditText)findViewById(R.id.namingText);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Name, t.getText().toString());
                populateSharedPreferences(editor);
                Intent intent;
                intent = new Intent(NamePet.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    protected void populateSharedPreferences(SharedPreferences.Editor editor)
    {
        editor.putInt(Pizza, 0);
        editor.putInt(IceCream, 0);
        editor.putInt(Fries, 0);
        editor.putInt(Cookie, 0);
        editor.putInt(Soda, 0);
        editor.putInt(HotDog, 0);
        editor.putInt(Bandage, 0);
        editor.putInt(Pills, 0);

        editor.putInt(PizzaPrice, 5);
        editor.putInt(IceCreamPrice, 10);
        editor.putInt(FriesPrice, 20);
        editor.putInt(CookiePrice, 50);
        editor.putInt(SodaPrice, 100);
        editor.putInt(HotDogPrice, 250);
        editor.putInt(BandagePrice, 50);
        editor.putInt(PillsPrice, 150);

    }
}