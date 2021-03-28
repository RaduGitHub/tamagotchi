package com.example.tomogatchi2;

import android.content.SharedPreferences;

public class Data {

    public static int foodCounter, sleepCounter, walkCounter, sickCounter, cleanCounter;
    public static int tick = 2000;

    public static boolean stepsActive = false;
    public static boolean foodActive = false;
    public static boolean sleepActive = false;
    public static boolean sickActive = false;
    public static boolean cleanActive = false;
    public static boolean feeding = false;
    public static boolean caring = false;

    //sharedpreferences header
    public static final String MyPREFERENCES = "MyPrefs" ;

    //Pet Caracteristics
    public static final String Name = "nameKey";
    public static final String Height = "HeightKey";
    public static final String Weight = "WeightKey";
    public static final String DaysAlive = "DaysAliveKey";
    public static final String Happiness = "HappinessKey";
    public static final String Health = "HealthKey";
    public static final String DayBorn = "DayBornKey";

    //Money
    public static final String Money = "MoneyKey";

    //foodstore total items
    public static final String FoodstoreTotalItems = "FoodstoreTotalItemsKey";

    //food
    public static final String Pizza = "PizzaKey";
    public static final String IceCream = "IceCreamKey";
    public static final String Fries = "FriesKey";
    public static final String Cookie = "CookieKey";
    public static final String Soda = "SodaKey";
    public static final String HotDog = "HotDogKey";

    //food price
    public static final String PizzaPrice = "PizzaPriceKey";
    public static final String IceCreamPrice = "IceCreamPriceKey";
    public static final String FriesPrice = "FriesPriceKey";
    public static final String CookiePrice = "CookiePriceKey";
    public static final String SodaPrice = "SodaPriceKey";
    public static final String HotDogPrice = "HotDogPriceKey";

    public static final String PizzaHappy = "PizzaHappyKey";
    public static final String IceCreamHappy = "IceCreamHappyKey";
    public static final String FriesHappy = "FriesHappyKey";
    public static final String CookieHappy = "CookieHappyKey";
    public static final String SodaHappy = "SodaHappyKey";
    public static final String HotDogHappy = "HotDogHappyKey";

    //drugstore total items
    public static final String DrugstoreTotalItems = "DrugstoreTotalItemsKey";

    //drugs
    public static final String Bandage = "BandageKey";
    public static final String Pills = "PillsKey";

    //drugs prices
    public static final String BandagePrice = "BandagePriceKey";
    public static final String PillsPrice = "PillsPriceKey";

    public static final String BandageHappy = "BandageHappyKey";
    public static final String PillsHappy = "PillsHappyKey";

    public static final String Default = "default";

    public static int fragments;

}
