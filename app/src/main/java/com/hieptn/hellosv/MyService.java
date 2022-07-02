package com.hieptn.hellosv;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {


    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String intentData = intent.getStringExtra(MainActivity.KEY_INTENT);
        startSendNotification(intentData);
        return START_NOT_STICKY;
    }

    private void startSendNotification(String intentData) {

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, MainActivity.CHANNEL_ID)
                .setContentTitle("Xin chao Service")
                .setContentText(intentData)
                .setSmallIcon(R.drawable.ic_baseline_backup_24)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
    }
}









