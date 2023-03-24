package com.example.notesapp.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private static SharedPreferences sharedPreferences(Context context){
        return context.getSharedPreferences("notePref",Context.MODE_PRIVATE);
    }
    public static void addToSharedPref(Context context, String name, String email,String phone) {
        sharedPreferences(context).edit().putString("name", name).apply();
        sharedPreferences(context).edit().putString("email", email).apply();
        sharedPreferences(context).edit().putString("phone", phone).apply();
    }

//    public static boolean isUserLogin(Context context) {
//        return sharedPreferences(context).contains("name");
//    }

    public static String getNameFromSharedPref(Context context){
        return sharedPreferences(context).getString("name","");
    }
    public static String getEmailFromSharedPref(Context context){
        return sharedPreferences(context).getString("email","email");
    }
    public static String getPhoneFromSharedPref(Context context){
        return sharedPreferences(context).getString("phone","phone");
    }

    public static boolean isLogin(Context context) {
        if (!getNameFromSharedPref(context).isEmpty())
            return sharedPreferences(context).getBoolean("isLoginKey", true);
        else
            return false;

    }

    public static void deletePref(Context context){
       sharedPreferences(context).edit().clear().apply();
    }



}
