package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddSetTime extends AppCompatActivity implements   TimePickerDialog.OnTimeSetListener {

    private TextView tv1;
    private Button bt1,bt2;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    int hour,minute;
    int hour_x,minute_x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set_time);

        tv1 = (TextView)findViewById(R.id.addsettime_tv1);
        bt1 = (Button)findViewById(R.id.addsittime_bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c =Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddSetTime.this,AddSetTime.this,hour,minute,true);
                timePickerDialog.show();
            }
        });

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour_x=hourOfDay;
        minute_x = minute;
        tv1.setText(hour_x+":"+minute_x);

    }
}