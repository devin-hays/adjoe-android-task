package com.example.adjoe.utils;

import android.content.Context;

public interface ActiveUser {

    Long getLastTimeActive(Context context);

    void saveLastTimeActive(Context context, Long time);
}
