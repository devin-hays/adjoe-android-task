package com.example.adjoe.utils;

import android.content.Context;
import android.app.Notification;
public interface NotiBuilder {

    Notification getNewNotification(Context context, long minutes);
}
