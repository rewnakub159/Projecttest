package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PetTag extends AppCompatActivity {

    Home_Menu homeMenu = new Home_Menu();
    String users = homeMenu.user.toString();

    DatabaseReference reference;
    TextView tv1,tv2,tv3;
    String petnumber,petname;
    ImageButton imb1,imb2;

    String tagstatus,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_tag);

        imb1 = (ImageButton)findViewById(R.id.imageButton1);
        imb2 = (ImageButton)findViewById(R.id.imageButton2);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);

        petnumber = getIntent().getStringExtra("petnumber");
        petname = getIntent().getStringExtra("petname");

        tv1.setText(petname);

        reference= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pet_DB i = dataSnapshot.getValue(Pet_DB.class);
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
               /**switch (petnumber){
                   case "pet1":
                       reference= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber).child();
                       break;
                   case "pet2":
                       break;
                   case "pet2":
                       break;
               }**/

            }
        });
        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("ลบ TAG");
                builder.setMessage("คุณต้องการลบรายการนี้ใช่หรือไม่");
                builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                reference= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Pet_DB i = dataSnapshot.getValue(Pet_DB.class);
                        i.setId("0");
                        i.setTagstatus("0");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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
        });


    }
}