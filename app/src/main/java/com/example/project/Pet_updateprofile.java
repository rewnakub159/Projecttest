package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Pet_updateprofile extends AppCompatActivity {
    Home_Menu loginPage1 = new Home_Menu();
    String users = loginPage1.user.toString();
    String petnumber;
    String petname;
    String breed;
    String gender;
    String weight;
    String type;
    String age;
    EditText etpetname;
    EditText etbreed;
    EditText etweight;
    EditText etage;

    RadioGroup rg1,rg2;
    RadioButton r1;

    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_updateprofile);

        petnumber = getIntent().getStringExtra("petnumber");
        petname = getIntent().getStringExtra("petname");
        breed = getIntent().getStringExtra("breed");
        gender = getIntent().getStringExtra("gender");
        weight = getIntent().getStringExtra("weight");
        type = getIntent().getStringExtra("type");
        age = getIntent().getStringExtra("age");

        etpetname = (EditText)findViewById(R.id.et1);
        etbreed = (EditText)findViewById(R.id.et2);
        etweight = (EditText)findViewById(R.id.et3);
        etage = (EditText)findViewById(R.id.et4);

        rg1 = (RadioGroup)findViewById(R.id.rg1);
        rg2 = (RadioGroup)findViewById(R.id.rg2);

        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);

        if(type.equals("สุนัข")){
            rg1.check(R.id.radioButton1);
        }else if(type.equals("แมว")){
            rg1.check(R.id.radioButton2);
        }

        if(gender.equals("ผู้")){
            rg2.check(R.id.radioButton3);
        }else if(type.equals("เมีย")){
            rg2.check(R.id.radioButton4);
        }

        etpetname.setText(petname);
        etbreed.setText(breed);
        etweight.setText(weight);
        etage.setText(age);

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
                        gender ="ผู้";
                        break;
                    case R.id.radioButton4:
                        gender ="เมีย";
                        break;
                }
            }
        });
       bt2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Pet_updateprofile.this,Pet_Re1.class);
               startActivity(intent);
           }
       });
       bt1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
               builder.setTitle("ลบรายการ");
               builder.setMessage("คุณต้องการบันทึกการเปลี่ยนแปลงครั้งนี้ใช่หรื่อไม่");
               builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       final Pet_DB pet_db = new Pet_DB();
                       pet_db.setPetnumber(petnumber);
                       pet_db.setPetname(etpetname.getText().toString());
                       pet_db.setType(type);
                       pet_db.setGender(gender);
                       pet_db.setBreed(etbreed.getText().toString());
                       pet_db.setAge(etage.getText().toString());
                       pet_db.setWeight(etweight.getText().toString());
                       new Pet_Firebase().updaeBook(petnumber, pet_db, new Pet_Firebase.DataStatus() {
                           @Override
                           public void DataIsLoaded(List<Pet_DB> books, List<String> keys) {

                           }

                           @Override
                           public void DataIsInserted() {

                           }

                           @Override
                           public void DataIsUpdated() {
                               Toast.makeText(Pet_updateprofile.this,"แก้ไขข้อมูลสำเร็จ",Toast.LENGTH_LONG).show();
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


    }
}