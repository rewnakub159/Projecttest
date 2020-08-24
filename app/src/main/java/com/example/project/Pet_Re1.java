package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class Pet_Re1 extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_re1);
        mRecyclerView = (RecyclerView)findViewById(R.id.macRecycler);
       new Pet_Firebase().readBooks(new Pet_Firebase.DataStatus() {
           @Override
           public void DataIsLoaded(List<Pet_DB> i, List<String> k) {
               new Pet_RecycleView_Config().setConfig(mRecyclerView, Pet_Re1.this,i,k);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //Top Menu
        getMenuInflater().inflate(R.menu.user_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Menu Action
        switch (item.getItemId()){
            case R.id.addUser:
                startActivity(new Intent(this,Pet_addPet.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

