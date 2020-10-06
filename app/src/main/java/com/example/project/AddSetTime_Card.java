package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddSetTime_Card extends AppCompatActivity implements   TimePickerDialog.OnTimeSetListener {

    SelectMac_ReCon selectMac_reCon = new SelectMac_ReCon();
    String macname = SelectMac_ReCon.macname.toString();

    EditText et1;
    TextView tv1;
    Button bt1, bt2;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    int hour, minute;
    int hour_x, minute_x;
    int i = 0;

    private String time;
    private String volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set_time__card);

        bt1 = (Button) findViewById(R.id.addsettime_bt1);
        bt2 = (Button) findViewById(R.id.addsettime_bt2);


        tv1 = (TextView) findViewById(R.id.addsettime_tv1);
        et1 = (EditText) findViewById(R.id.addsetime_et1);

        time = getIntent().getStringExtra("time");
        volume = getIntent().getStringExtra("amount");
        if (time == "null" && volume == "null") {
            et1.setText("");
            tv1.setText("");
        } else {
            et1.setText(volume);
            tv1.setText(time);
        }
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddSetTime_Card.this, AddSetTime_Card.this, hour, minute, true);
                timePickerDialog.show();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = FirebaseDatabase.getInstance().getReference("time").orderByChild("settime1").orderByChild("settime2").orderByChild("settime3")
                        .orderByChild("settime4").orderByChild("settime5").equalTo(macname);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() > 1){
                            Toast.makeText(AddSetTime_Card.this,"เวลานี้ถูกใช้แล้ว",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

                                             }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour_x=hourOfDay;
        minute_x = minute;
        String sHour,sminute;
        sHour = String.valueOf(hourOfDay);
        sminute= String.valueOf(minute);
        if (hour_x<10){
            sHour = "0"+hour_x;
        }
        if (minute_x<10){
            sminute = "0"+minute_x;
        }
        tv1.setText(sHour +":"+ sminute);
        i=1;
    }
}