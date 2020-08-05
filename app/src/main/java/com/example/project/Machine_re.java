package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Machine_re extends AppCompatActivity {

    private RecyclerView macRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_re);
        macRecyclerView = (RecyclerView)findViewById(R.id.macre1);
        new FirebaseMac().readBooks(new FirebaseMac.DataStatus() {
            @Override
            public void DataIsLoaded(List<Machine_db> machine_dbs, List<String> keys) {
                new MacReConfig().setConfig(macRecyclerView,Machine_re.this,machine_dbs,keys);
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
}