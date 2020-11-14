package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
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

public class RegisterPage extends AppCompatActivity {
    DatabaseReference reference;
    Button dialogbt1,dialogbt2;
    TextView dialogtv1,dialogtv2;
    Dialog dialog,dialog_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        dialog = new Dialog(this);
        dialog_1 = new Dialog(this);

        EditText e1 = (EditText)findViewById(R.id.et1);
        EditText e2 = (EditText)findViewById(R.id.editText2);
        EditText e3 = (EditText)findViewById(R.id.editText3);
        EditText e4 = (EditText)findViewById(R.id.editText4);

        e1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, 0, 0);
        e1.setHint(" USERNAME");
        e2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock1, 0, 0, 0);
        e2.setHint(" PASSWORD");
        e3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock, 0, 0, 0);
        e3.setHint(" CONFIRMPASSWORD");
        e4.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_note, 0, 0, 0);
        e4.setHint(" EMAIL");

        final String userName = e1.getText().toString().trim();
        final String password = e2.getText().toString().trim();
        final  String password2 = e3.getText().toString().trim();
        final String email = e4.getText().toString().trim();

        Button bt1 = (Button)findViewById(R.id.button);
        Button bt2 = (Button)findViewById(R.id.button3);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
 bt1.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         EditText e1 = (EditText)findViewById(R.id.et1);
         EditText e2 = (EditText)findViewById(R.id.editText2);
         EditText e3 = (EditText)findViewById(R.id.editText3);
         EditText e4 = (EditText)findViewById(R.id.editText4);
         final String userName = e1.getText().toString().trim();
         final String password = e2.getText().toString().trim();
         final  String password2 = e3.getText().toString().trim();
         final String email = e4.getText().toString().trim();
         Query query = FirebaseDatabase.getInstance().getReference().child("user").orderByChild("username").equalTo(userName);
         query.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 if (dataSnapshot.getChildrenCount() ==1){ //check username
                     Toast.makeText(RegisterPage.this,"ชื้อนี้ถูกใช้แล้ว",Toast.LENGTH_LONG).show();

                 }else { register1();}

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

     }
 });
    }


    public void register1(){
        EditText e1 = (EditText)findViewById(R.id.et1);
        EditText e2 = (EditText)findViewById(R.id.editText2);
        EditText e3 = (EditText)findViewById(R.id.editText3);
        EditText e4 = (EditText)findViewById(R.id.editText4);
        final String username = e1.getText().toString().trim();
        final String password = e2.getText().toString().trim();
        final  String password2 = e3.getText().toString().trim();
        final String email = e4.getText().toString().trim();


if (!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)==!TextUtils.isEmpty(password2)) {

    DatabaseReference dbregister = FirebaseDatabase.getInstance().getReference("user") ;
    Register_DB register1 = new Register_DB(username,password,email);
    dbregister.child(username).setValue(register1); //set name part

    reference= FirebaseDatabase.getInstance().getReference("pet").child(username);
    String petnumber = "pet1";
    String petname = "null";
    String gender = "null";
    String type = "null";
    String breed = "null";
    String age = "null";
    String weight = "null";
    String id = "0";
    String tagstatus = "0";
    Pet_DB pet_db1 = new Pet_DB(petnumber,petname,gender,type,breed,age,weight,id,tagstatus);
    reference.child(petnumber).setValue(pet_db1);

    reference= FirebaseDatabase.getInstance().getReference("pet").child(username);
    petnumber = "pet2";
    Pet_DB pet_db2 = new Pet_DB(petnumber,petname,gender,type,breed,age,weight,id,tagstatus);
    reference.child(petnumber).setValue(pet_db2);

    reference= FirebaseDatabase.getInstance().getReference("pet").child(username);
    petnumber = "pet3";
    Pet_DB pet_db3 = new Pet_DB(petnumber,petname,gender,type,breed,age,weight,id,tagstatus);
    reference.child(petnumber).setValue(pet_db3);
    dialog1();

     }
else if (password != password2){Toast.makeText(this,"รหัสผ่านไม่ตรงกัน",Toast.LENGTH_LONG).show();

}
else{ Toast.makeText(this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
    }


    }
    public  void dialog1(){
        dialog.setContentView(R.layout.dialog_1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogbt1 = (Button)dialog.findViewById(R.id.dibt1);
        dialogtv1 = (TextView)dialog.findViewById(R.id.tv1);
        dialogtv1.setText("สมัครสมาชิกสำเร็จ");
        dialogbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }
    public  void dialog2(){
        dialog_1.setContentView(R.layout.dialog_2);
        dialog_1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogbt2 = (Button)dialog_1.findViewById(R.id.dibt1_2);
        dialogtv2 = (TextView)dialog_1.findViewById(R.id.tv1_2);
        dialogtv2.setText("ชื้อนี้ถูกใช้แล้ว");
        dialogbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_1.dismiss();

            }
        });
        dialog_1.show();
    }

}
