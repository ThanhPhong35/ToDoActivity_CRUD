package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.model.AlarmReciever;
import com.example.model.ThongTin;

import java.util.Calendar;

public class NewTodoActivity extends AppCompatActivity {
    Button btSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
        btSave=findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH)+1;
                int date=calendar.get(Calendar.DAY_OF_MONTH);
                int h=calendar.get(Calendar.HOUR);
                int m=calendar.get(Calendar.MINUTE);
                calendar.set(Calendar.HOUR_OF_DAY,h);
                calendar.set(Calendar.MINUTE,m);
                AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                String ddate=date+"/"+month+"/"+year;
                ThongTin tt=new ThongTin("Ha dat hang",ddate,"dat 3 cai ao");
                Intent intent = new Intent(NewTodoActivity.this, AlarmReciever.class);
                intent.setAction("AAA");
                intent.putExtra("title",tt.getTitle());
                String d=tt.getDesc()+"-"+tt.getDate();
                intent.putExtra("content", d);
                final int flag =  Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                        PendingIntent.FLAG_UPDATE_CURRENT |
                                PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT;
                PendingIntent pendingIntent=PendingIntent.getBroadcast(NewTodoActivity.this,
                        0,intent, flag);
                am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                finish();
            }
        });
    }
}