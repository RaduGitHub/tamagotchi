package com.example.tomogatchi2.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tomogatchi2.Controllers.NamePetController;
import com.example.tomogatchi2.R;

public class NamePetView extends AppCompatActivity {

    Button b1;
    NamePetController namePetController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_pet);
        b1=(Button)findViewById(R.id.submitButton);
        namePetController = new NamePetController();


        EditText t = (EditText)findViewById(R.id.namingText);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = t.getText().toString();
                namePetController.namePet(name);
                Intent intent;
                intent = new Intent(NamePetView.this, MainActivityView.class);
                startActivity(intent);
                finish();
            }
        });
    }



}