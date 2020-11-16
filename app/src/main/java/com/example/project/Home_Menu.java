package com.example.project;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home_Menu extends AppCompatActivity {
    public static String user;
    TextView tv1;
    Button bt1;
    CardView c1,c2,c3,c4,c5;
    final Machine_DB machine_db = new Machine_DB();
    DatabaseReference reference;

    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    public static final String CHANNEL_3_ID = "channel3";
    public static final String CHANNEL_4_ID = "channel4";

    public static final String CHANNEL_5_ID = "channel5";
    public static final String CHANNEL_6_ID = "channel6";

    private NotificationManagerCompat notificationManager;

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_menu);

        notificationManager = NotificationManagerCompat.from(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");
            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel 2");
            NotificationChannel channel3 = new NotificationChannel(
                    CHANNEL_3_ID,
                    "Channel 3",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel3.setDescription("This is Channel 3");
            NotificationChannel channel4 = new NotificationChannel(
                    CHANNEL_4_ID,
                    "Channel 4",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel4.setDescription("This is Channel 5");
            NotificationChannel channel5 = new NotificationChannel(
                    CHANNEL_5_ID,
                    "Channel 5",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel5.setDescription("This is Channel 5");
            NotificationChannel channel6 = new NotificationChannel(
                    CHANNEL_6_ID,
                    "Channel 6",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel6.setDescription("This is Channel 6");




            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel3);
            manager.createNotificationChannel(channel4);
            manager.createNotificationChannel(channel5);
            manager.createNotificationChannel(channel6);
        }

        c1 = (CardView)findViewById(R.id.homecard_feednow);
        c2 = (CardView)findViewById(R.id.homecard_settime);
        c3 = (CardView)findViewById(R.id.homecard_pet_re);
        c4 = (CardView)findViewById(R.id.homecard_mac_re);
        c5 = (CardView)findViewById(R.id.homecard_camera);

        tv1= (TextView)findViewById(R.id.tvuser);
        bt1=(Button)findViewById(R.id.bt_logout);
        sessionManager = new SessionManager(getApplicationContext());
        String username = sessionManager.getUsername();
        tv1.setText(username);
        user = tv1.getText().toString();



        reference = FirebaseDatabase.getInstance().getReference("machineprofile").child(tv1.getText().toString()).child("mac1");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String food_level = (String) dataSnapshot.child("food_level").getValue();
                String status = (String) dataSnapshot.child("status").getValue();
                String volume = (String) dataSnapshot.child("volume").getValue();
                String macname = (String) dataSnapshot.child("name").getValue();
                if (food_level.equals("0")) {
                    Notification notification = new NotificationCompat.Builder(Home_Menu.this, CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.ic_food)
                            .setContentTitle(macname)
                            .setContentText("อาหารหมด")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();
                    notificationManager.notify(1, notification);
                }
                if (status.equals("1")) {
                    Notification notification = new NotificationCompat.Builder(Home_Menu.this, CHANNEL_2_ID)
                            .setSmallIcon(R.drawable.ic_food)
                            .setContentTitle(macname)
                            .setContentText("กำลังจ่ายอาหาร ปริมาณ " + volume +"  กรัม")
                            .setPriority(NotificationCompat.PRIORITY_LOW)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();
                    notificationManager.notify(2, notification);
                }






            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("machineprofile").child(tv1.getText().toString()).child("mac2");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String food_level = (String) dataSnapshot.child("food_level").getValue();
                String status = (String) dataSnapshot.child("status").getValue();
                String volume = (String) dataSnapshot.child("volume").getValue();
                String macname = (String) dataSnapshot.child("name").getValue();
                if (food_level.equals("0")) {
                    Notification notification = new NotificationCompat.Builder(Home_Menu.this, CHANNEL_3_ID)
                            .setSmallIcon(R.drawable.ic_food)
                            .setContentTitle(macname)
                            .setContentText("อาหารหมด")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();
                    notificationManager.notify(3, notification);
                }
                if (status.equals("1")) {
                    Notification notification = new NotificationCompat.Builder(Home_Menu.this, CHANNEL_4_ID)
                            .setSmallIcon(R.drawable.ic_food)
                            .setContentTitle(macname)
                            .setContentText("กำลังจ่ายอาหาร ปริมาณ " + volume +"  กรัม")
                            .setPriority(NotificationCompat.PRIORITY_LOW)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();
                    notificationManager.notify(4, notification);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        reference = FirebaseDatabase.getInstance().getReference("machineprofile").child(tv1.getText().toString()).child("mac3");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String food_level = (String) dataSnapshot.child("food_level").getValue();
                String status = (String) dataSnapshot.child("status").getValue();
                String volume = (String) dataSnapshot.child("volume").getValue();
                String macname = (String) dataSnapshot.child("name").getValue();
                if (food_level.equals("0")) {
                    Notification notification = new NotificationCompat.Builder(Home_Menu.this, CHANNEL_5_ID)
                            .setSmallIcon(R.drawable.ic_food)
                            .setContentTitle(macname)
                            .setContentText("อาหารหมด")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();
                    notificationManager.notify(5, notification);
                }
                if (status.equals("1")) {
                    Notification notification = new NotificationCompat.Builder(Home_Menu.this, CHANNEL_6_ID)
                            .setSmallIcon(R.drawable.ic_food)
                            .setContentTitle(macname)
                            .setContentText("กำลังจ่ายอาหาร ปริมาณ " + volume +"  กรัม")
                            .setPriority(NotificationCompat.PRIORITY_LOW)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();
                    notificationManager.notify(6, notification);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });














        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Log out");
                builder.setMessage("are you sure to Log out");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionManager.setLogin(false);
                        sessionManager.setUsername("");
                        startActivity(new Intent(getApplicationContext(),LoginPage1.class));
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });




        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SelectMac_feedNow.class));
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SelectMac_setTime.class));
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Pet_Re1.class));
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Machine_Profile_Re.class));
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SelectMac_cameraRe.class));

            }
        });
    }
}