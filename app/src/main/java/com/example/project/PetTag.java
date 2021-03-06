package com.example.project;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PetTag extends AppCompatActivity {

    Pet_RecycleView_Config pet_RecycleView_Config = new Pet_RecycleView_Config();
    String petnumber = pet_RecycleView_Config.petnumbers.toString();

    Home_Menu homeMenu = new Home_Menu();
    String users = homeMenu.user.toString();

    Dialog dialog;
    DatabaseReference reference;
    Task<Void> reference2;
    TextView tv1,tv2,tv3,dialogtv1,tv4;
    ImageButton imb1,imb2;
    Button dialogbt1,bt3;
    String mName;




    String tagstatus,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_tag);


        mName= getIntent().getStringExtra("name");

        dialog = new Dialog(this);

        imb1 = (ImageButton)findViewById(R.id.imageButton1);
        imb2 = (ImageButton)findViewById(R.id.imageButton2);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv);

        bt3 = (Button)findViewById(R.id.bt3);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //petnumber = getIntent().getStringExtra("petnumber");
        //petname = getIntent().getStringExtra("petname");


        tv4.setText(mName);
        reference= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pet_DB i = dataSnapshot.getValue(Pet_DB.class);
                String petname = i.getPetname();
                tv1.setText(petname);
                tagstatus = i.getTagstatus();
                id = i.getId();
                tv2.setText(id);
                switch (tagstatus){
                    case "0":
                        tv3.setText("ยังไม่มีการเพิ่ม TAG");
                        break;
                    case "1":
                        tv3.setText("กำลังใช้งาน");
                        break;
                    case "2":
                        tv3.setText("รอการยืนยัน");
                        break;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        imb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv3.getText() == "รอการยืนยัน"){
                    Toast.makeText(PetTag.this,"กรุณารอการยืนยันจากเครื่อง",Toast.LENGTH_LONG).show();
                }else if (tv3.getText() == "ยังไม่มีการเพิ่ม TAG"){
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("เพิ่ม TAG");
                    builder.setMessage("คุณต้องการเพิ่ม TAG ใช่หรือไม่");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(PetTag.this, MyReceiver.class);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                    PetTag.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                            switch (petnumber){
                                case "pet1":
                                    reference2 = FirebaseDatabase.getInstance().getReference("pet").child("menu").child(mName).child("rfidStatus").setValue("1");
                                    reference2= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child("tagstatus").setValue("2");
                                    break;
                                case "pet2":
                                    reference2 = FirebaseDatabase.getInstance().getReference("pet").child("menu").child(mName).child("rfidStatus").setValue("2");
                                    reference2= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child("tagstatus").setValue("2");
                                    break;
                                case "pet3":
                                    reference2 = FirebaseDatabase.getInstance().getReference("pet").child("menu").child(mName).child("rfidStatus").setValue("3");
                                    reference2= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child("tagstatus").setValue("2");
                                    break;
                            }

                        }
                    });
                    builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                        }

                else if (tv3.getText() == "กำลังใช้งาน"){
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("เปลี่ยน Tag");
                    builder.setMessage("คุณต้องการเปลี่ยน TAG ใช่หรือไม่");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (petnumber){
                                case "pet1":
                                    reference2 = FirebaseDatabase.getInstance().getReference("pet").child("menu").child("fd001").child("rfidStatus").setValue("1");
                                    reference2= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child("tagstatus").setValue("2");
                                    break;
                                case "pet2":
                                    reference2 = FirebaseDatabase.getInstance().getReference("pet").child("menu").child("fd001").child("rfidStatus").setValue("2");
                                    reference2= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child("tagstatus").setValue("2");
                                    break;
                                case "pet3":
                                    reference2 = FirebaseDatabase.getInstance().getReference("pet").child("menu").child("fd001").child("rfidStatus").setValue("3");
                                    reference2= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child("tagstatus").setValue("2");
                                    break;
                            }



                        }
                    });
                    builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }



            }
        });
        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("ลบ Tag");
                    builder.setMessage("คุณต้องการลบ TAG ใช่หรือไม่");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reference2 = FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child("tagstatus").setValue("0");
                            reference2 = FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child("id").setValue("0");

                        }
                    });
                    builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                });


        }
    public void dialog1(){
        dialog.setContentView(R.layout.dialog_1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogbt1 = (Button)dialog.findViewById(R.id.dibt1);
        dialogtv1 = (TextView)dialog.findViewById(R.id.tv1);
        dialogtv1.setText("เพิ่ม TAG สำเร็จ");
        dialogbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void  addTag(){

    }
}