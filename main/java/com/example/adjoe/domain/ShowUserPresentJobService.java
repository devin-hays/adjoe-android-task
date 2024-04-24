package com.example.adjoe.domain;

import android.Manifest;
import android.app.Notification;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.adjoe.utils.ActiveUser;
import com.example.adjoe.utils.ActiveUserHelper;
import com.example.adjoe.utils.NotiBuilder;
import com.example.adjoe.utils.NotiHelper;
import com.example.adjoe.utils.TimeFormatter;
import com.example.adjoe.utils.TimeHelper;

public class ShowUserPresentJobService extends JobService {

    private Handler handler;
    private Runnable runnable;
    private ActiveUser activeUser = new ActiveUserHelper();
    private TimeFormatter timeFormatter = new TimeHelper();
    private NotiBuilder notiHelper = new NotiHelper();;
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        updateNotification();
        return false;
    }

    private void updateNotification() {
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                showNotification();
                handler.postDelayed(this, 5000);
            }
        };
        handler.post(runnable);
    }

    private void showNotification() {
        long minutes = timeFormatter.getLastActiveTimeInMinutes(activeUser.getLastTimeActive(this));

        Notification notification = notiHelper.getNewNotification(this, minutes);

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Request permission in the activity, otherwise you might not see the notifications on certain devices.
            return;
        }
        manager.notify(1, notification);
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
        return true;
    }

}
