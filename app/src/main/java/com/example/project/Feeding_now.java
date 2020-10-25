package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feeding_now extends AppCompatActivity {
    private DatabaseReference reference;

    SelectMac_feedNow_Con selectMac_reCon =new SelectMac_feedNow_Con();
    String macname = SelectMac_feedNow_Con.macname.toString();
    Home_Menu loginPage1 = new Home_Menu();
    String users = loginPage1.user.toString();
    String key;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feeding_now);


        final EditText editText =(EditText)findViewById(R.id.et1);
        Button button = (Button)findViewById(R.id.bt1);
        Button button1 = (Button)findViewById(R.id.bt2);
        reference=FirebaseDatabase.getInstance().getReference("machineprofile").child(users);

       button.setOnClickListener(new View.OnClickListener() {
        final String food = editText.getText().toString().trim();
           @Override
           public void onClick(View v) {
               int s;
               s = Integer.parseInt(editText.getText().toString());
               if (s >= 100) {
                   reference.child(macname).child("volume").setValue(editText.getText().toString());
                   reference.child(macname).child("status").setValue("1");
               }else{
                   Toast.makeText(Feeding_now.this, "ปริมาณอาหารน้อยกว่า 100 กรัม", Toast.LENGTH_LONG).show();}


           }
       });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}