package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QrCode_addmachine extends AppCompatActivity {
    private DatabaseReference reference,reference2,reference3,reference4;
    private Button scan_button,bt1,bt2,bt3;
    EditText et1;
    TextView tv1,tv2;
    final Machine_DB machine_db = new Machine_DB();
    Home_Menu loginPage1 = new Home_Menu();
    String users = loginPage1.user.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code_addmachine);
        et1=(EditText) findViewById(R.id.addmac_et1);
        scan_button = (Button) findViewById(R.id.scan_button);

        bt2=(Button)findViewById(R.id.button4);
        bt3=(Button)findViewById(R.id.addmac_bt3) ;


        final Activity activity = this;

        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(activity);

                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);

                integrator.setPrompt("Scan");

                integrator.setCameraId(0);

                integrator.setBeepEnabled(false);

                integrator.setBarcodeImageEnabled(false);

                integrator.initiateScan();

            }

        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String macname = et1.getText().toString();
                Query query = FirebaseDatabase.getInstance().getReference("machine2").orderByChild("name").equalTo(macname);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() == 1) {

                            final String macname = et1.getText().toString();
                            reference= FirebaseDatabase.getInstance().getReference("machine2").child(macname);
                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String userType = (String) dataSnapshot.child("status").getValue();
                                    if (userType.equals("use")) {
                                        Toast.makeText(QrCode_addmachine.this, "เครื่องนี้ได้ถูกใช้ไปแล้ว", Toast.LENGTH_LONG).show();
                                    }else{



                                        reference = FirebaseDatabase.getInstance().getReference("machineprofile").child(users).child("mac1");
                                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                                                    Machine_DB i = dataSnapshot.getValue(Machine_DB.class);
                                                    if (i.getName().equals("null")) {
                                                        machine_db.setMacnumber("mac1");
                                                        machine_db.setName(et1.getText().toString());
                                                        machine_db.setVolume("null");
                                                        machine_db.setVolume_now("null");
                                                        machine_db.setFood_level("null");
                                                        machine_db.setHistory("null");
                                                        machine_db.setStatus("0");
                                                        machine_db.setNotification("0");
                                                        machine_db.setTimeno("00:00");
                                                        machine_db.setCameralink("null");
                                                        machine_db.setCreatedate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));

                                                        reference=FirebaseDatabase.getInstance().getReference("machine2");
                                                        reference.child(et1.getText().toString()).child("status").setValue("use");
                                                        reference.child(et1.getText().toString()).child("username").setValue(users);


                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac1");
                                                        String settime = "settime1";
                                                        String volume = "0";
                                                        String time = "00:00";
                                                        String status = "0";
                                                        SetTime_Db setTime_db = new SetTime_Db(settime,time,volume,status);
                                                        reference2.child(settime).setValue(setTime_db);


                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac1");
                                                        settime = "settime2";
                                                        SetTime_Db setTime_db2 = new SetTime_Db(settime,time,volume,status);
                                                        reference2.child(settime).setValue(setTime_db2);




                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac1");
                                                        settime = "settime3";
                                                        SetTime_Db setTime_db3 = new SetTime_Db(settime,time,volume,status);
                                                        reference2.child(settime).setValue(setTime_db3);


                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac1");
                                                        settime = "settime4";
                                                        SetTime_Db setTime_db4 = new SetTime_Db(settime,time,volume,status);
                                                        reference2.child(settime).setValue(setTime_db4);


                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac1");
                                                        settime = "settime5";
                                                        SetTime_Db setTime_db5 = new SetTime_Db(settime,time,volume,status);
                                                        reference2.child(settime).setValue(setTime_db5);


                                                        new SelectMac_Firebase().updaeBook("mac1", machine_db, new SelectMac_Firebase.DataStatus() {
                                                            @Override
                                                            public void DataIsLoaded(List<Machine_DB> books, List<String> keys) {

                                                            }

                                                            @Override
                                                            public void DataIsInserted() {

                                                            }

                                                            @Override
                                                            public void DataIsUpdated() {
                                                                Toast.makeText(QrCode_addmachine.this, "update successfully", Toast.LENGTH_LONG).show();
                                                            }

                                                            @Override
                                                            public void DataIsDeleted() {

                                                            }
                                                        });

                                                    }

                                                     else {

                                                        reference = FirebaseDatabase.getInstance().getReference("machineprofile").child(users).child("mac2");
                                                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                                                                    Machine_DB i = dataSnapshot.getValue(Machine_DB.class);
                                                                    if (i.getName().equals("null")) {
                                                                        machine_db.setMacnumber("mac2");
                                                                        machine_db.setName(et1.getText().toString());
                                                                        machine_db.setVolume("null");
                                                                        machine_db.setVolume_now("null");
                                                                        machine_db.setFood_level("null");
                                                                        machine_db.setHistory("null");
                                                                        machine_db.setStatus("0");
                                                                        machine_db.setNotification("0");
                                                                        machine_db.setTimeno("00:00");
                                                                        machine_db.setCameralink("null");
                                                                        machine_db.setCreatedate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));

                                                                        reference=FirebaseDatabase.getInstance().getReference("machine2");
                                                                        reference.child(et1.getText().toString()).child("status").setValue("use");
                                                                        reference.child(et1.getText().toString()).child("username").setValue(users);

                                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac2");
                                                                        String settime = "settime1";
                                                                        String volume = "0";
                                                                        String time = "00:00";
                                                                        String status = "0";
                                                                        SetTime_Db setTime_db = new SetTime_Db(settime,time,volume,status);
                                                                        reference2.child(settime).setValue(setTime_db);


                                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac2");
                                                                        settime = "settime2";
                                                                        SetTime_Db setTime_db2 = new SetTime_Db(settime,time,volume,status);
                                                                        reference2.child(settime).setValue(setTime_db2);




                                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac2");
                                                                        settime = "settime3";
                                                                        SetTime_Db setTime_db3 = new SetTime_Db(settime,time,volume,status);
                                                                        reference2.child(settime).setValue(setTime_db3);


                                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac2");
                                                                        settime = "settime4";
                                                                        SetTime_Db setTime_db4 = new SetTime_Db(settime,time,volume,status);
                                                                        reference2.child(settime).setValue(setTime_db4);


                                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac2");
                                                                        settime = "settime5";
                                                                        SetTime_Db setTime_db5 = new SetTime_Db(settime,time,volume,status);
                                                                        reference2.child(settime).setValue(setTime_db5);


                                                                        new SelectMac_Firebase().updaeBook("mac2", machine_db, new SelectMac_Firebase.DataStatus() {
                                                                            @Override
                                                                            public void DataIsLoaded(List<Machine_DB> books, List<String> keys) {

                                                                            }

                                                                            @Override
                                                                            public void DataIsInserted() {

                                                                            }

                                                                            @Override
                                                                            public void DataIsUpdated() {
                                                                                Toast.makeText(QrCode_addmachine.this, "update successfully", Toast.LENGTH_LONG).show();
                                                                            }

                                                                            @Override
                                                                            public void DataIsDeleted() {

                                                                            }
                                                                        });

                                                                    }

                                                                    else {

                                                                        reference = FirebaseDatabase.getInstance().getReference("machineprofile").child(users).child("mac3");
                                                                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                                                                                    Machine_DB i = dataSnapshot.getValue(Machine_DB.class);
                                                                                    if (i.getName().equals("null")) {
                                                                                        machine_db.setMacnumber("mac3");
                                                                                        machine_db.setName(et1.getText().toString());
                                                                                        machine_db.setVolume("null");
                                                                                        machine_db.setVolume_now("null");
                                                                                        machine_db.setFood_level("null");
                                                                                        machine_db.setHistory("null");
                                                                                        machine_db.setStatus("0");
                                                                                        machine_db.setNotification("0");
                                                                                        machine_db.setTimeno("00:00");
                                                                                        machine_db.setCameralink("null");
                                                                                        machine_db.setCreatedate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));

                                                                                        reference=FirebaseDatabase.getInstance().getReference("machine3");
                                                                                        reference.child(et1.getText().toString()).child("status").setValue("use");
                                                                                        reference.child(et1.getText().toString()).child("username").setValue(users);


                                                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac3");
                                                                                        String settime = "settime1";
                                                                                        String volume = "0";
                                                                                        String time = "00:00";
                                                                                        String status = "0";
                                                                                        SetTime_Db setTime_db = new SetTime_Db(settime,time,volume,status);
                                                                                        reference2.child(settime).setValue(setTime_db);


                                                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac3");
                                                                                        settime = "settime2";
                                                                                        SetTime_Db setTime_db2 = new SetTime_Db(settime,time,volume,status);
                                                                                        reference2.child(settime).setValue(setTime_db2);




                                                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac3");
                                                                                        settime = "settime3";
                                                                                        SetTime_Db setTime_db3 = new SetTime_Db(settime,time,volume,status);
                                                                                        reference2.child(settime).setValue(setTime_db3);


                                                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac3");
                                                                                        settime = "settime4";
                                                                                        SetTime_Db setTime_db4 = new SetTime_Db(settime,time,volume,status);
                                                                                        reference2.child(settime).setValue(setTime_db4);


                                                                                        reference2= FirebaseDatabase.getInstance().getReference("time").child("mac3");
                                                                                        settime = "settime5";
                                                                                        SetTime_Db setTime_db5 = new SetTime_Db(settime,time,volume,status);
                                                                                        reference2.child(settime).setValue(setTime_db5);


                                                                                        new SelectMac_Firebase().updaeBook("mac1", machine_db, new SelectMac_Firebase.DataStatus() {
                                                                                            @Override
                                                                                            public void DataIsLoaded(List<Machine_DB> books, List<String> keys) {

                                                                                            }

                                                                                            @Override
                                                                                            public void DataIsInserted() {

                                                                                            }

                                                                                            @Override
                                                                                            public void DataIsUpdated() {
                                                                                                Toast.makeText(QrCode_addmachine.this, "update successfully", Toast.LENGTH_LONG).show();
                                                                                            }

                                                                                            @Override
                                                                                            public void DataIsDeleted() {

                                                                                            }
                                                                                        });

                                                                                    }

                                                                                    else {


                                                                                        Toast.makeText(QrCode_addmachine.this, "เกินขีดจำกัดที่จะเพิ่มเครื่องได้", Toast.LENGTH_LONG).show();


                                                                                    }

                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                            }
                                                                        });

                                                                    }

                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });


                                                    }

                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });




                                        reference=FirebaseDatabase.getInstance().getReference("machine2");
                                        reference.child(macname).child("status").setValue("use");
                                        reference.child(macname).child("username").setValue(users);










                                        Toast.makeText(QrCode_addmachine.this, "เพื่อมเครื่องให้อาหารสำเร็จ", Toast.LENGTH_LONG).show();

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    finish();
                                }
                            });

                        }else {
                            Toast.makeText(QrCode_addmachine.this, "ไม่มีเครื่องนี้อยู่ในระบบ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(result != null){

            if(result.getContents()== null){

                Toast.makeText(this, "You cancell scanning", Toast.LENGTH_LONG).show();

            }


            else{
                String qrtex = result.getContents();
                Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
                et1.setText(qrtex);
            }

        }

        else {

            super.onActivityResult(requestCode, resultCode, data);


        }
    }
}