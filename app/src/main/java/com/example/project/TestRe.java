package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

public class TestRe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_re);

       /** Intent intent = new Intent(this,MyReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),1234,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+ 10 * 1000,pi);

    }**/
    }
}