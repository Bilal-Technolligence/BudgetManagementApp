package com.example.budgetmanagementapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Save {

    //Saving Values
    public static void save(Context context, String name, String value) {
        SharedPreferences s =context.getSharedPreferences("BMA",Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = s.edit();
        edt.putString(name,value);
        edt.apply();

    }

    public static String read(Context context,String name,String defaultValue) {
        SharedPreferences s =context.getSharedPreferences("BMA",Context.MODE_PRIVATE);
        return s.getString(name, defaultValue);
    }


}
