package com.hieptn.hellosv;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnStop;
    EditText edt;
    public static final String CHANNEL_ID = "CHANNEL_ID";
    public static final String KEY_INTENT = "KEY_INTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        edt = findViewById(R.id.edt);

        btnStart.setOnClickListener(view -> {
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));

        });

        btnStop.setOnClickListener(view -> {
//            Intent intent = new Intent(this, MyAccessibilityService.class);
//            startService(intent);

            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("demo.xm.com.xmfunsdkdemo");
            if (launchIntent != null) {
                startActivity(launchIntent);//null pointer check in case package name was not found
            }

        });

        createChannelNotification();

    }

    private void createChannelNotification() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel name service example", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setSound(null, null);
        channel.setShowBadge(false);
        NotificationManager manager = getSystemService(NotificationManager.class);
        if (manager != null) {
            manager.createNotificationChannel(channel);
        }
    }


    private void stopRunningService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private void runningService() {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra(KEY_INTENT, edt.getText().toString().trim());
        startService(intent);
    }
}