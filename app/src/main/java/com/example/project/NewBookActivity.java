package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class NewBookActivity extends AppCompatActivity {
    private EditText plain1,plain2,plain3;
    private Button bt1,bt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        plain1=(EditText)findViewById(R.id.addBookPlain1);
        plain2=(EditText)findViewById(R.id.addBookPlain2);
        plain3=(EditText)findViewById(R.id.addBookPlain3);
        bt1=(Button)findViewById(R.id.addBookBt1);
        bt2=(Button)findViewById(R.id.addBookBt2);
    }
}