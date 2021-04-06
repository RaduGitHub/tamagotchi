package com.example.tomogatchi2.Models;

import android.content.SharedPreferences;

public class CharacteristicModel {
    Integer happiness;
    String name;
    Integer height;
    Integer weight;
    Integer daysAlive;
    Integer health;
    Integer money;

    public CharacteristicModel(SharedPreferences sharedPreferences){
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        this.happiness = sharedPreferences.getInt(Data.Happiness, 0);
        this.name = sharedPreferences.getString(Data.Name, "Cho");
        this.height = sharedPreferences.getInt(Data.Height, 0);
        this.weight = sharedPreferences.getInt(Data.Weight, 0);
        this.daysAlive = sharedPreferences.getInt(Data.DaysAlive, 0);
        this.health = sharedPreferences.getInt(Data.Health, 0);
        this.money = sharedPreferences.getInt(Data.Money, 0);
    }

    public Integer getHappiness(){
        return this.happiness;
    }

    public String getName(){
        return this.name;
    }

    public Integer getHeight(){
        return this.height;
    }

    public Integer getWeight(){
        return this.weight;
    }

    public Integer getDaysAlive(){
        return this.daysAlive;
    }

    public Integer getHealth(){
        return this.health;
    }

    public Integer getMoney(){ return this.money; }

}
