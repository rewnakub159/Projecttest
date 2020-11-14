package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Pet_addPet extends AppCompatActivity {

    Home_Menu loginPage1 = new Home_Menu();
    String users = loginPage1.user.toString();

    DatabaseReference reference;


    String petnumber;
    String petname;
    String breed;
    String gender;
    String weight;
    String type;
    String age;

    EditText et1,et2,et3,et4;
    RadioGroup rg1,rg2;
    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_add_pet);


        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText)findViewById(R.id.et4);

        bt1 = (Button) findViewById(R.id.dibt1);
        bt2 = (Button) findViewById(R.id.bt2);

        rg1 = (RadioGroup)findViewById(R.id.rg1);
        rg2 = (RadioGroup)findViewById(R.id.rg2);

        rg1.check(R.id.radioButton1);
        type ="สุนัข";
        rg2.check(R.id.radioButton3);
        gender = "ผู้";

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton1:
                        type ="สุนัข";
                        break;
                    case R.id.radioButton2:
                        type ="แมว";
                        break;
                }
            }
        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton3:
                        gender = "ผู้";
                        break;
                    case R.id.radioButton4:
                        gender = "เมีย";
                        break;
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et1.getText())&&!TextUtils.isEmpty(et2.getText())&&!TextUtils.isEmpty(et3.getText())&&!TextUtils.isEmpty(et4.getText())){
                    addPet();
                    finish();
                }else{Toast.makeText(Pet_addPet.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_LONG).show();}

            }
        });

    }
     public void addPet(){

         Query query = FirebaseDatabase.getInstance().getReference("pet").child(users).orderByChild("petname").equalTo(et1.getText().toString());
         query.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 if (dataSnapshot.getChildrenCount() == 1) {
                     Toast.makeText(Pet_addPet.this, "ชื่อนี้ถูกใช้แล้ว", Toast.LENGTH_LONG).show();
                 } else {

                     reference = FirebaseDatabase.getInstance().getReference("pet").child(users).child("pet1");
                     reference.addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                             for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                                 Pet_DB i = dataSnapshot.getValue(Pet_DB.class);
                                 if (i.getPetname().equals("null")) {
                                     final Pet_DB pet_db = new Pet_DB();
                                     pet_db.setPetnumber("pet1");
                                     pet_db.setPetname(et1.getText().toString());
                                     pet_db.setType(type);
                                     pet_db.setGender(gender);
                                     pet_db.setBreed(et2.getText().toString());
                                     pet_db.setAge(et4.getText().toString());
                                     pet_db.setWeight(et3.getText().toString());
                                     pet_db.setId("0");
                                     pet_db.setTagstatus("0");
                                     new Pet_Firebase().updaeBook("pet1", pet_db, new Pet_Firebase.DataStatus() {
                                         @Override
                                         public void DataIsLoaded(List<Pet_DB> books, List<String> keys) {

                                         }

                                         @Override
                                         public void DataIsInserted() {

                                         }

                                         @Override
                                         public void DataIsUpdated() {
                                             Toast.makeText(Pet_addPet.this,"แก้ไขข้อมูลสำเร็จ",Toast.LENGTH_LONG).show();
                                         }

                                         @Override
                                         public void DataIsDeleted() {

                                         }
                                     });



                                 }else{reference = FirebaseDatabase.getInstance().getReference("pet").child(users).child("pet2");
                                     reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                         @Override
                                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                             for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                                                 Pet_DB i = dataSnapshot.getValue(Pet_DB.class);
                                                 if (i.getPetname().equals("null")) {
                                                     final Pet_DB pet_db = new Pet_DB();
                                                     pet_db.setPetnumber("pet2");
                                                     pet_db.setPetname(et1.getText().toString());
                                                     pet_db.setType(type);
                                                     pet_db.setGender(gender);
                                                     pet_db.setBreed(et2.getText().toString());
                                                     pet_db.setAge(et4.getText().toString());
                                                     pet_db.setWeight(et3.getText().toString());
                                                     pet_db.setId("0");
                                                     pet_db.setTagstatus("0");
                                                     new Pet_Firebase().updaeBook("pet2", pet_db, new Pet_Firebase.DataStatus() {
                                                         @Override
                                                         public void DataIsLoaded(List<Pet_DB> books, List<String> keys) {

                                                         }

                                                         @Override
                                                         public void DataIsInserted() {

                                                         }

                                                         @Override
                                                         public void DataIsUpdated() {
                                                             Toast.makeText(Pet_addPet.this,"แก้ไขข้อมูลสำเร็จ",Toast.LENGTH_LONG).show();
                                                         }

                                                         @Override
                                                         public void DataIsDeleted() {

                                                         }
                                                     });



                                                 }else{reference = FirebaseDatabase.getInstance().getReference("pet").child(users).child("pet3");
                                                     reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                         @Override
                                                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                             for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                                                                 Pet_DB i = dataSnapshot.getValue(Pet_DB.class);
                                                                 if (i.getPetname().equals("null")) {
                                                                     final Pet_DB pet_db = new Pet_DB();
                                                                     pet_db.setPetnumber("pet3");
                                                                     pet_db.setPetname(et1.getText().toString());
                                                                     pet_db.setType(type);
                                                                     pet_db.setGender(gender);
                                                                     pet_db.setBreed(et2.getText().toString());
                                                                     pet_db.setAge(et4.getText().toString());
                                                                     pet_db.setWeight(et3.getText().toString());
                                                                     pet_db.setId("0");
                                                                     pet_db.setTagstatus("0");
                                                                     new Pet_Firebase().updaeBook("pet3", pet_db, new Pet_Firebase.DataStatus() {
                                                                         @Override
                                                                         public void DataIsLoaded(List<Pet_DB> books, List<String> keys) {

                                                                         }

                                                                         @Override
                                                                         public void DataIsInserted() {

                                                                         }

                                                                         @Override
                                                                         public void DataIsUpdated() {
                                                                             Toast.makeText(Pet_addPet.this,"แก้ไขข้อมูลสำเร็จ",Toast.LENGTH_LONG).show();
                                                                         }

                                                                         @Override
                                                                         public void DataIsDeleted() {

                                                                         }
                                                                     });
                                                                 }else{Toast.makeText(Pet_addPet.this,"สัตว์เลี้ยงเต็มแล้ว",Toast.LENGTH_LONG).show();}
                                                             }

                                                         }
                                                         @Override
                                                         public void onCancelled(@NonNull DatabaseError databaseError) {

                                                         }
                                                     });}
                                             }

                                         }
                                         @Override
                                         public void onCancelled(@NonNull DatabaseError databaseError) {

                                         }
                                     });}
                             }

                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) {

                         }
                     });


                 }
             }
             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });


     }
     }


