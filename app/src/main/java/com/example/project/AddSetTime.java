package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
    int i =0;
    SelectMac_ReCon selectMac_reCon=new SelectMac_ReCon();
    String macname = SelectMac_ReCon.macname.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_set_time);

        tv1 = (TextView)findViewById(R.id.addsettime_tv1);
        et1 = (EditText)findViewById(R.id.addsetime_et1);
        bt1 = (Button)findViewById(R.id.addsettime_bt1);
        bt2 = (Button)findViewById(R.id.addsettime_bt4);
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
                final String volume = et1.getText().toString();
                final String time = tv1.getText().toString().trim();
                Query query = FirebaseDatabase.getInstance().getReference("time").child(macname).orderByChild("settime").equalTo(time);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() == 1){ //check username
                            Toast.makeText(AddSetTime.this,"เวลานี้ถูกใช้แล้ว",Toast.LENGTH_LONG).show();Toast.makeText(AddSetTime.this,"เวลานี้ถูกใช้แล้ว",Toast.LENGTH_LONG).show();
                        }else if (i==1){
                            addtime();
                        }
                        else if (!TextUtils.isEmpty(volume)){
                            Toast.makeText(AddSetTime.this,"กรุณากำหนดปริมาณ",Toast.LENGTH_LONG).show();
                        }
                        else { Toast.makeText(AddSetTime.this,"กรุณาตั้งเวลา",Toast.LENGTH_LONG).show();
                        }

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                    finish();
                }

        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour_x=hourOfDay;
        minute_x = minute;
        tv1.setText(hour_x+":"+minute_x);
        i=1;
    }
    public void addtime(){

        int s;
        s = Integer.parseInt(et1.getText().toString());
        if (s>= 100){
        SetTime_Db book = new SetTime_Db();
        book.setSettime(tv1.getText().toString());
        book.setVolume(et1.getText().toString());
        book.setStatus("0");

        new SetTime_Firebase().addBook(book, new SetTime_Firebase.DataStatus() {
            @Override
            public void DataIsLoaded(List<SetTime_Db> books, List<String> keys) {
                Toast.makeText(AddSetTime.this, "เพิ่มเวลาสำเร็จ", Toast.LENGTH_LONG).show();
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

    }else {
            Toast.makeText(AddSetTime.this,"ปริมาณอาหารน้อยกว่า 100 กรัม",Toast.LENGTH_LONG).show();
        }
}
}