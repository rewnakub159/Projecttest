package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class SelectMac_cameraRe extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_mac_camera_re);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.foodnowre1);
        new SelectMac_Firebase().readBooks(new SelectMac_Firebase.DataStatus() {
            @Override
            public void DataIsLoaded(List<Machine_DB> books, List<String> keys) {
                new SelectMac_cameraCon().setConfig(mRecyclerView, SelectMac_cameraRe.this,books,keys);
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
    }
