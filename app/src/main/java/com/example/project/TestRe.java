package com.example.project;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


public class TestRe extends AppCompatActivity {

    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_re);
          String NOTIFICATION_CHANNEL_ID = "10001" ;
          String default_notification_channel_id = "default" ;
        bt1 =(Button)findViewById(R.id.dibt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(TestRe.this,"2356")
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("John's Android Studio Tutorials")
                        .setContentText("A video has just arrived!");

                // Creates the intent needed to show the notification
                Intent notificationIntent = new Intent(TestRe.this, TestRe.class);
                PendingIntent contentIntent = PendingIntent.getActivity(TestRe.this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(contentIntent);

                // Add as notification
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0, builder.build());


            }
        });

    }

}