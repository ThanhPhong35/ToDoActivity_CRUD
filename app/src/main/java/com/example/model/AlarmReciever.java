package com.example.model;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.MainActivity;
import com.example.R;

import java.util.Date;

public class AlarmReciever extends BroadcastReceiver {
    private final static String CHANNEL_ID3 = "channel 3";
    private final static String CHANNEL_ID4 = "channel 4";
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(
                        context.NOTIFICATION_SERVICE);
        if (intent.getAction().equals("ABC")) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel1 = new NotificationChannel(
                        CHANNEL_ID3,
                        "Channel 3",
                        NotificationManager.IMPORTANCE_HIGH);
                channel1.setDescription("Mieu ta 3");
                notificationManager.createNotificationChannel(channel1);
            }
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context, CHANNEL_ID3)
                            .setContentTitle(intent.getStringExtra("name"))
                            .setContentText(intent.getStringExtra("noidung"))
                            .setSmallIcon(R.drawable.ic_notifications)
                            .setColor(Color.BLUE)
                            .setDefaults(NotificationCompat.DEFAULT_SOUND)
                            .setCategory(NotificationCompat.CATEGORY_ALARM);
            notificationManager.notify(getNotificationid(),builder.build());
        }else if(intent.getAction().equals("AAA")){
            Log.e("Rev","rev");
//            NotificationManager manager =
//                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel2 = new NotificationChannel(
                        CHANNEL_ID4,
                        "Channel 4",
                        notificationManager.IMPORTANCE_HIGH
                );
                channel2.setDescription("This is Channel 1");
                notificationManager.createNotificationChannel(channel2);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,
                    CHANNEL_ID4)
                    .setSmallIcon(R.drawable.ic_circle)
                    .setContentTitle(intent.getStringExtra("title"))
                    .setContentText(intent.getStringExtra("content"))
                    .setColor(Color.BLUE)
                    .setDefaults(NotificationCompat.DEFAULT_SOUND)
                    .setCategory(NotificationCompat.CATEGORY_ALARM);
            notificationManager.notify(getNotificationid(),builder.build());
        }


    }
    private int getNotificationid(){
        int time=(int)new Date().getTime();
        return time;
    }
}
