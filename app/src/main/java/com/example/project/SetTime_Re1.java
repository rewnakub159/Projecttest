package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class SetTime_Re1 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time__re1);
        mRecyclerView = (RecyclerView) findViewById(R.id.settime_recyclerview);
        new SetTime_Firebase().readBooks(new SetTime_Firebase.DataStatus() {
            @Override
            public void DataIsLoaded(List<SetTime_Db> books, List<String> keys) {
                new SetTime_ReCon().setConfig(mRecyclerView, SetTime_Re1.this, books, keys);

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
                startActivity(new Intent(this,AddSetTime.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }