package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class SelectMac_Re1 extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mac__re1);
        mRecyclerView = (RecyclerView) findViewById(R.id.selectmac_recyclerview);
        new SelectMac_Firebase().readBooks(new SelectMac_Firebase.DataStatus() {
            @Override
            public void DataIsLoaded(List<Machine_DB> books, List<String> keys) {
                new SelectMac_ReCon().setConfig(mRecyclerView, SelectMac_Re1.this,books,keys);
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

