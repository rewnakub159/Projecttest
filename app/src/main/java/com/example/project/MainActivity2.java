package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_books);
        new FirebaseDatabaseHelper().readBooks(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Register_DB> books, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView,MainActivity2.this,books,keys);

            }

            @Override
            public void DataIsserted() {

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
                startActivity(new Intent(this,RegisterPage.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}