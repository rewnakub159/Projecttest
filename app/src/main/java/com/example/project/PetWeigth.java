package com.example.project;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PetWeigth extends AppCompatActivity {

    Pet_RecycleView_Config pet_RecycleView_Config = new Pet_RecycleView_Config();
    String petnumber = pet_RecycleView_Config.petnumbers.toString();

    Home_Menu homeMenu = new Home_Menu();
    String users = homeMenu.user.toString();

    Dialog dialog;
    DatabaseReference reference;
    Task<Void> reference2;
    TextView tv1,tv2,tv3,tv4;
    ImageButton imb2;
    Button dialogbt1,bt3;
    String mName,weightstatus,weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_weigth);


        mName= getIntent().getStringExtra("name");

        dialog = new Dialog(this);


        imb2 = (ImageButton)findViewById(R.id.imageButton2);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);

        bt3 = (Button)findViewById(R.id.bt3);
        tv4.setText(mName);



        //petnumber = getIntent().getStringExtra("petnumber");
        //petname = getIntent().getStringExtra("petname");



        reference= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pet_DB i = dataSnapshot.getValue(Pet_DB.class);
                String petname = i.getPetname();
                weight = i.getWeight();
                tv1.setText(petname);
                tv2.setText(weight);
                weightstatus = i.getWeightstatus();
                switch (weightstatus) {
                    case "0":
                        tv3.setText("ชั้งแล้ว");
                        break;
                    case "1":
                        tv3.setText("กรุณานำสัตว์เลี้ยงชั่งน้ำหนัก");
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("ชั่งน้ำหนัก");
                    builder.setMessage("คุณต้องการชั่งน้ำหนักใช่หรือไม่");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            switch (petnumber){
                                case "pet1":
                                    reference2 = FirebaseDatabase.getInstance().getReference("pet").child("menu").child(mName).child("lcStatus").setValue("1");
                                    reference2= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child("weightstatus").setValue("1");
                                    break;
                                case "pet2":
                                    reference2 = FirebaseDatabase.getInstance().getReference("pet").child("menu").child(mName).child("lcStatus").setValue("2");
                                    reference2= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child("weightstatus").setValue("");
                                    break;
                                case "pet3":
                                    reference2 = FirebaseDatabase.getInstance().getReference("pet").child("menu").child(mName).child("lcStatus").setValue("3");
                                    reference2= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child("weightstatus").setValue("1");
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

        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}