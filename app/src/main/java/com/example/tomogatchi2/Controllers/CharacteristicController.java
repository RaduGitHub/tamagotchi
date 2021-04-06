package com.example.tomogatchi2.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tomogatchi2.Models.CharacteristicModel;
import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.Utils.MyContext;

public class CharacteristicController {
    CharacteristicModel characteristicModel;
    SharedPreferences sharedPreferences;
    Context context;

    public CharacteristicController(){
        this.context = MyContext.getAppContext();
        sharedPreferences = this.context.getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);
        this.characteristicModel = new CharacteristicModel(sharedPreferences);
    }

    public Integer getHappiness(){
        return characteristicModel.getHappiness();
    }

    public String getName(){
        return characteristicModel.getName();
    }

    public Integer getHeight(){
        return characteristicModel.getHeight();
    }

    public Integer getWeight(){
        return characteristicModel.getWeight();
    }

    public Integer getDaysAlive(){
        return characteristicModel.getDaysAlive();
    }

    public Integer getHealth(){
        return characteristicModel.getHealth();
    }

    public Integer getMoney(){ return characteristicModel.getMoney(); }
}
