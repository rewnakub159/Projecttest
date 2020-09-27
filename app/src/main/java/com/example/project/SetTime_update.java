package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class SetTime_update extends AppCompatActivity implements   TimePickerDialog.OnTimeSetListener  {
    Button bt1, bt2, bt3, bt4;
    EditText et1;
    TextView tv1;
    int hour,minute;
    int hour_x,minute_x;
    private String time;
    private String amount;
    SelectMac_ReCon selectMac_reCon=new SelectMac_ReCon();
    String macname = SelectMac_ReCon.macname.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time_update);

        time = getIntent().getStringExtra("time");
        amount = getIntent().getStringExtra("amount");

        bt1 = (Button) findViewById(R.id.addsettime_bt1);
        bt2 = (Button) findViewById(R.id.addsettime_bt2);
        bt3 = (Button) findViewById(R.id.addsettime_bt3);
        bt4 = (Button) findViewById(R.id.addsettime_bt4);

        et1 = (EditText) findViewById(R.id.addsetime_et1);
        et1.setText(amount);
        tv1 = (TextView) findViewById(R.id.addsettime_tv1);
        tv1.setText(time);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final SetTime_Db setTime_db = new SetTime_Db();
               setTime_db.setSettime(tv1.getText().toString());
               setTime_db.setAmountfood(et1.getText().toString());
                final String time = tv1.getText().toString().trim();
                Query query = FirebaseDatabase.getInstance().getReference("time").child(macname).orderByChild("settime").equalTo(time);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() == 1){ //check username
                            Toast.makeText(SetTime_update.this,"Time is use",Toast.LENGTH_LONG).show();
                        }else {
                            new SetTime_Firebase().updaeBook(time, setTime_db, new SetTime_Firebase.DataStatus() {
                            @Override
                            public void DataIsLoaded(List<SetTime_Db> books, List<String> keys) {

                            }

                            @Override
                            public void DataIsInserted() {

                            }

                            @Override
                            public void DataIsUpdated() {
                                Toast.makeText(SetTime_update.this,"update successfully",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void DataIsDeleted() {

                            }
                        });


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

               finish();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SetTime_Firebase().deleteBook(time, new SetTime_Firebase.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<SetTime_Db> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(SetTime_update.this,"deleted successfully",Toast.LENGTH_LONG).show();
                    }
                });
                finish();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();return;
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c =Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(SetTime_update.this,SetTime_update.this,hour,minute,true);
                timePickerDialog.show();
            }
        });
    }
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour_x=hourOfDay;
        minute_x = minute;
        tv1.setText(hour_x+":"+minute_x);
    }
    private int getIndex(Spinner spinner, String item) {
        int index = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(item)) {
                index = i;
                break;
            }
        }
        return  index;

    }

}