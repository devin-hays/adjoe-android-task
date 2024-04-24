package com.example.adjoe;;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.example.adjoe.domain.ShowUserPresentJobService;
import com.example.adjoe.receiver.UserPresentReceiver;

public class AdjoeApplication extends Application {

    private static final long JOB_INTERVAL = 15 * 60 * 1000;
    private UserPresentReceiver userPresentReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver();
        createNotificationChannel();
        scheduleJob(this);
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_USER_PRESENT);
        userPresentReceiver = new UserPresentReceiver();
        registerReceiver(userPresentReceiver, filter);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (userPresentReceiver != null) {
            unregisterReceiver(userPresentReceiver);
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    this.getString(R.string.noti_channel_id),
                    this.getString(R.string.noti_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription(this.getString(R.string.noti_channel_desc));

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void scheduleJob(Context context) {
        ComponentName componentName = new ComponentName(context, ShowUserPresentJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(123, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(JOB_INTERVAL)
                .setPersisted(true)
                .build();
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        scheduler.schedule(jobInfo);
    }
}
