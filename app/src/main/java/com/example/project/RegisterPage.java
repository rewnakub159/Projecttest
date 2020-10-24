package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
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
                     Toast.makeText(RegisterPage.this,"username is use",Toast.LENGTH_LONG).show();
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
    Toast.makeText(this,"0000",Toast.LENGTH_LONG).show();
    finish(); }
else if (password != password2){Toast.makeText(this,"2",Toast.LENGTH_LONG).show();}
else{ Toast.makeText(this,"1",Toast.LENGTH_LONG).show();}


    }
}
