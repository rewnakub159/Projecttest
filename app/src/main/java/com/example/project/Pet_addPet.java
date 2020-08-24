package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Pet_addPet extends AppCompatActivity {
    EditText et1,et2,et3,et4,et5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_add_pet);


        et1 = (EditText)findViewById(R.id.petAdd_name_et1);
        et2 = (EditText)findViewById(R.id.petAdd_breed_et2);
        et3 = (EditText)findViewById(R.id.petAdd_age_et3);
        et4 = (EditText)findViewById(R.id.petAdd_weigth_et4);


        Button bt1,bt2;
        bt1 = (Button)findViewById(R.id.pet_add_bt1);
        bt2 = (Button)findViewById(R.id.pet_add_bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pet_DB book = new Pet_DB();
                book.setName(et1.getText().toString());
                book.setBreed(et2.getText().toString());
                book.setAge(et3.getText().toString());
                book.setWeigth(et4.getText().toString());
            new Pet_Firebase().addBook(book, new Pet_Firebase.DataStatus() {
                @Override
                public void DataIsLoaded(List<Pet_DB> books, List<String> keys) {
                    Toast.makeText(Pet_addPet.this,"successfully",Toast.LENGTH_LONG).show();

                }

                @Override
                public void DataIsInserted() {

                }

                @Override
                public void DataIsUpdated() {

                }

                @Override
                public void DataIsDeleted() {

                }
            });
                finish();
            }
        });

    }
}