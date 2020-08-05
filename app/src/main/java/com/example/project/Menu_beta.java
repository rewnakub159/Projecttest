package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_beta extends AppCompatActivity {

    private Button bt1,bt2,bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_beta);

        bt1 = (Button)findViewById(R.id.menu_bt1);
        bt2 = (Button)findViewById(R.id.menu_bt2);
        bt3 = (Button)findViewById(R.id.menu_bt3);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu_beta.this,MainActivity3.class);
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


    }
}