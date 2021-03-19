package com.example.tomogatchi2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.tomogatchi2.Data.MyPREFERENCES;
import static com.example.tomogatchi2.Data.Name;

public class MainActivity extends AppCompatActivity {
    //public static final String MyPREFERENCES = "MyPrefs" ;
    //public static final String Name = "nameKey";
    TextView Money;
    SharedPreferences sharedPreferences;
    Context context;
    boolean stepsActive = false;
    StepCounter fragment;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        fragment = StepCounter.newInstance();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        addFragment(fragment);

    }

    //Shows the money updates when coming back to the screen
    @Override
    protected void onResume(){
        super.onResume();
        Money = (TextView)findViewById(R.id.textView3);
        Money.setText(String.valueOf(sharedPreferences.getInt(Data.Money, 0)));
    }

    public void IncreaseCoin(View view){

        Money.setText(String.valueOf(Integer.parseInt(Money.getText().toString()) + 1));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Data.Money, Integer.parseInt(Money.getText().toString()));
        editor.commit();


        i++;
        if(i%5 == 0)
        {
            walkButton();
        }
    }

    public void ChangeToShop(View view){
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }

    public void ChangeToDrugstore(View view){
        Intent intent = new Intent(this, Drugstore.class);
        startActivity(intent);
    }

    public void ChangeToCharacteristics(View view)
    {
        Intent intent = new Intent(this, Characteristics.class);
        startActivity(intent);
    }

    public void addFragment(Fragment fragment)
    {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container_view0, fragment).addToBackStack(null).show(fragment);
        fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();
    }

    public void walkButton()
    {
        ImageButton bt = findViewById(R.id.imageButton0);
        bt.setVisibility(View.VISIBLE);
        bt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.show(fragment);
                Data.fragments += 1;
                fragmentTransaction.commit();
                stepsActive = true;
                walkButtonUndo();
            }
        });
    }

    public void walkButtonUndo(){
        ImageButton bt = findViewById(R.id.imageButton0);

        bt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.hide(fragment);
                bt.setVisibility(View.INVISIBLE);
                fragmentTransaction.commit();
            }
        });

    }


    public void stepsDialog(String current, String total) {
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle(current + "/" + total);
        dialog.show();
    }

}