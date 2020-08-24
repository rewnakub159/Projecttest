package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        final EditText editText =(EditText)findViewById(R.id.et1);

        Button button = (Button)findViewById(R.id.bt1);
        Button button1 = (Button)findViewById(R.id.bt2);
        reference=FirebaseDatabase.getInstance().getReference("machine").child("1");

       button.setOnClickListener(new View.OnClickListener() {
        final String food = editText.getText().toString().trim();
           @Override
           public void onClick(View v) {
                   reference.child("fd001").child("volume").setValue(editText.getText().toString());
                   reference.child("fd001").child("status").setValue("1");


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