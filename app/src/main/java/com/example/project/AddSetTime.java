package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

public class AddSetTime extends AppCompatActivity implements   TimePickerDialog.OnTimeSetListener {

     TextView tv1;
     EditText et1;
     Button bt1,bt2;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    int hour,minute;
    int hour_x,minute_x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set_time);

        tv1 = (TextView)findViewById(R.id.addsettime_tv1);
        et1 = (EditText)findViewById(R.id.addsetime_et1);
        bt1 = (Button)findViewById(R.id.addsittime_bt1);
        bt2 = (Button)findViewById(R.id.addsettime_bt2);
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
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String time = tv1.getText().toString().trim();
                Query query = FirebaseDatabase.getInstance().getReference("time").child("fd001").orderByChild("settime").equalTo(time);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() == 1){ //check username
                            Toast.makeText(AddSetTime.this,"Time is use",Toast.LENGTH_LONG).show();
                        }else { addtime();
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
        tv1.setText(hour_x+":"+minute_x);
    }
    public void addtime(){


        SetTime_Db book = new SetTime_Db();
        book.setSettime(tv1.getText().toString());
        book.setName(et1.getText().toString());
        new SetTime_Firebase().addBook(book, new SetTime_Firebase.DataStatus() {
            @Override
            public void DataIsLoaded(List<SetTime_Db> books, List<String> keys) {
                Toast.makeText(AddSetTime.this, "successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }
}