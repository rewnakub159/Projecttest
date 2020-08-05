package com.example.project;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseMac {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBook;
    private List<Machine_db> books = new ArrayList<>();

    public interface  DataStatus {
        void DataIsLoaded(List<Machine_db> books,List<String> keys);
        void DataIsserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseMac() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceBook = mDatabase.getReference("user");
    }
    public void  readBooks(final FirebaseMac.DataStatus dataStatus){
        mReferenceBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keynode :dataSnapshot.getChildren()){
                    keys.add(keynode.getKey());
                    Machine_db book = keynode.getValue(Machine_db.class);
                    books.add(book);
                }
                dataStatus.DataIsLoaded(books ,keys);          }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
