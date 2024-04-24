package com.example.adjoe.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class ActiveUserHelper implements ActiveUser {

    static  final String TIME  = "active_time";

    @Override
    public Long getLastTimeActive(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return prefs.getLong(TIME, ((long) (0)));
    }

    @Override
    public void saveLastTimeActive(Context context, Long time) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putLong(TIME, time);
        editor.apply();
    }
}
