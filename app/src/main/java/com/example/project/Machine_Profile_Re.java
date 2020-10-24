package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class Machine_Profile_Re extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine__profile__re);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.machinepro_re1);
        new Machine_Profile_Firebase().readBooks(new Machine_Profile_Firebase.DataStatus() {
            @Override
            public void DataIsLoaded(List<Machine_DB> books, List<String> keys) {
                new Machine_Profile_con().setConfig(mRecyclerView, Machine_Profile_Re.this,books,keys);
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
                startActivity(new Intent(this,QrCode_addmachine.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
