package com.example.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Pet_Profile extends AppCompatActivity {




    Home_Menu homeMenu = new Home_Menu();
    String users = homeMenu.user.toString();

    DatabaseReference reference;
    TextView tvpetname;
    TextView tvbreed;
    TextView tvgender;
    TextView tvweight;
    TextView tvtype;
    TextView tvage;
    String petnumber;
    String petname;
    String breed;
    String gender;
    String weight;
    String type;
    String age;
    String id;
    String tagstatus;
    Button bt1;
    ImageButton imb1,imb2,imb3,imb4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet__profile);


       petnumber = getIntent().getStringExtra("petnumber");
        /**petname = getIntent().getStringExtra("petname");
        breed = getIntent().getStringExtra("breed");
        gender = getIntent().getStringExtra("gender");
        weight = getIntent().getStringExtra("weight");
        type = getIntent().getStringExtra("type");
        age = getIntent().getStringExtra("age");**/

        tvpetname = (TextView)findViewById(R.id.tvpetname);
        tvbreed = (TextView)findViewById(R.id.tvbreed);
        tvgender = (TextView)findViewById(R.id.tvgender);
        tvweight = (TextView)findViewById(R.id.tvweight);
        tvtype = (TextView)findViewById(R.id.tvtype);
        tvage = (TextView)findViewById(R.id.tvage);

        bt1=(Button)findViewById(R.id.bt2);
        imb1 =(ImageButton)findViewById(R.id.imageButton1);
        imb2 =(ImageButton)findViewById(R.id.imageButton2);
        imb3 =(ImageButton)findViewById(R.id.imageButton3);
        imb4 =(ImageButton)findViewById(R.id.imageButton4);



        reference= FirebaseDatabase.getInstance().getReference("pet").child(users).child(petnumber);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pet_DB i = dataSnapshot.getValue(Pet_DB.class);
                petname = i.getPetname();
                breed = i.getBreed();
                gender = i.getGender();
                weight = i.getWeight();
                type = i.getType();
                age = i.getAge();
                id = i.getId();
                tagstatus = i.getTagstatus();
                tvpetname.setText(petname);
                tvbreed.setText(breed);
                tvgender.setText(gender);
                tvweight.setText(weight);
                tvtype.setText(type);
                tvage.setText(age);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pet_Profile.this,Pet_updateprofile.class);
                intent.putExtra("petnumber",petnumber);
                intent.putExtra("petname",petname);
                intent.putExtra("type",type);
                intent.putExtra("breed",breed);
                intent.putExtra("gender",gender);
                intent.putExtra("weight",weight);
                intent.putExtra("age",age);
                intent.putExtra("id",id);
                intent.putExtra("tagstatus",tagstatus);
                startActivity(intent);
            }
        });
        imb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Pet_Profile.this,SelectMac_Tag.class);
                intent.putExtra("petnumber",petnumber);
                intent.putExtra("petname",petname);
                startActivity(intent);
            }
        });
        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("ลบรายการ");
                builder.setMessage("คุณต้องการลบรายการนี้ใช่หรือไม่");
                builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Pet_DB pet_db = new Pet_DB();
                        pet_db.setPetnumber(petnumber);
                        pet_db.setPetname("null");
                        pet_db.setType("null");
                        pet_db.setGender("null");
                        pet_db.setBreed("null");
                        pet_db.setAge("null");
                        pet_db.setWeight("null");
                        new Pet_Firebase().updaeBook(petnumber, pet_db, new Pet_Firebase.DataStatus() {
                            @Override
                            public void DataIsLoaded(List<Pet_DB> books, List<String> keys) {

                            }

                            @Override
                            public void DataIsInserted() {

                            }

                            @Override
                            public void DataIsUpdated() {
                                Toast.makeText(Pet_Profile.this,"ลบสัตว์เลี้ยงสำเร็จ",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void DataIsDeleted() {

                            }
                        });
                        finish();
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

        imb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pet_Profile.this,Selectmac_Weigth.class);
                intent.putExtra("petnumber",petnumber);
                intent.putExtra("petname",petname);
                startActivity(intent);
            }
        });
            }
}


