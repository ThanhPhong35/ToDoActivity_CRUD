package com.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.model.AlarmReciever;
import com.example.model.MyNotification;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button bt,bt2;
    NotificationManagerCompat managerCompat;
    TimePicker timePicker;
    Button btHen,btBo;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.bt);
        bt2 = findViewById(R.id.bt2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this,
                                MyNotification.CHANNEL_ID)
                                .setContentTitle("Tin nhan")
                                .setContentText("cafe nhe ban yeu")
                                .setColor(Color.RED)
                                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                                .setSmallIcon(R.drawable.ic_notifications)
                                .setCategory(NotificationCompat.CATEGORY_ALARM)
                                .setAutoCancel(true);
                managerCompat = NotificationManagerCompat.from(getApplicationContext());
                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                managerCompat.notify(getNotificationid(), builder.build());
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this,
                                MyNotification.CHANNEL_ID2)
                                .setContentTitle("Tin nhan 2")
                                .setContentText("Di kiem tra di dong")
                                .setColor(Color.RED)
                                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                                .setSmallIcon(R.drawable.ic_circle)
                                .setCategory(NotificationCompat.CATEGORY_ALARM)
                                .setAutoCancel(true);
                managerCompat = NotificationManagerCompat.from(getApplicationContext());
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                managerCompat.notify(getNotificationid(), builder.build());
            }
        });
        timePicker=findViewById(R.id.time);
        btBo=findViewById(R.id.btBo);
        btHen=findViewById(R.id.btHen);
        btHen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                Intent intent=new Intent(MainActivity.this, AlarmReciever.class);
                intent.setAction("ABC");
                intent.putExtra("name","To an An");
                intent.putExtra("noidung","mua 2 ao, 2 tat");
                alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                final int flag =  Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                        PendingIntent.FLAG_UPDATE_CURRENT |
                        PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT;
                pendingIntent=PendingIntent.getBroadcast(MainActivity.this,
                        0,intent, flag);
                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),pendingIntent);
            }
        });
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        NewTodoActivity.class);
                startActivity(intent);
            }
        });


    }
    private int getNotificationid(){
        int time=(int)new Date().getTime();
        return time;
    }

}