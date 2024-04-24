package com.example.adjoe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.example.adjoe.utils.ActiveUser;
import com.example.adjoe.utils.ActiveUserHelper;

public class UserPresentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
            ActiveUser activeUser = new ActiveUserHelper();
            activeUser.saveLastTimeActive(context, System.currentTimeMillis());
        }
    }
}
