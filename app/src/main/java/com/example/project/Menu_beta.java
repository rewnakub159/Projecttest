package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_beta extends AppCompatActivity {

    private Button bt1,bt2,bt3,bt4,bt5,bt6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_beta);
        LoginPage1 loginPage1;
        bt1 = (Button)findViewById(R.id.menu_bt1);
        bt2 = (Button)findViewById(R.id.menu_bt2);
        bt3 = (Button)findViewById(R.id.menu_bt3);
        bt4 = (Button)findViewById(R.id.menu_bt4);
        bt5 = (Button)findViewById(R.id.menu_bt5);
        bt6 = (Button)findViewById(R.id.menu_bt6);
        final String name;
        Intent intentName = getIntent();
        name = intentName.getStringExtra("username");
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu_beta.this, Feeding_now.class);
                startActivity(i);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu_beta.this,MainActivity2.class);
                startActivity(i);

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Pet_Re1.class);
                startActivity(i);

            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SelectMac_setTime.class);
                startActivity(i);

            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SetTime_Re1.class);
                startActivity(i);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), QrCode_addmachine.class);
                startActivity(i);
            }
        });

    }


}