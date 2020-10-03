package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrCode_addmachine extends AppCompatActivity {
    private DatabaseReference reference,reference2,reference3;
    private Button scan_button,bt1,bt2,bt3;
    EditText et1;
    TextView tv1,tv2;
    Machine_DB machine_db;
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
                                        reference=FirebaseDatabase.getInstance().getReference("machine2");
                                        reference.child(macname).child("status").setValue("use");
                                        reference.child(macname).child("username").setValue(users);




                                        reference2= FirebaseDatabase.getInstance().getReference("machineprofile").child(users);
                                        String name = et1.getText().toString().trim();
                                        String volume = "null";
                                        String volume_now = "null";
                                        String food_level = "null";
                                        String history = "null";
                                        String status = "0";
                                        Machine_DB machine_db = new Machine_DB(name,status,volume,volume_now,food_level,history);
                                        reference2.child(name).setValue(machine_db);

                                        reference3= FirebaseDatabase.getInstance().getReference("history").child(users);


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