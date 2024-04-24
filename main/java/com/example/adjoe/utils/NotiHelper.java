package com.example.adjoe.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.example.adjoe.R;
import com.example.adjoe.presentation.AdjoeActivity;

public class NotiHelper implements NotiBuilder {
    @Override
    public Notification getNewNotification(Context context, long minutes) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, AdjoeActivity.class), PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(context, context.getString(R.string.noti_channel_id))
                .setContentTitle(context.getString(R.string.noti_title))
                .setContentText(context.getString(R.string.noti_content, minutes))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(contentIntent)
                .build();
    }
}
