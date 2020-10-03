package com.example.project;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Machine_Profile_Firebase {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBook;
    private List<Machine_DB> books = new ArrayList<>();

    Home_Menu homeMenu = new Home_Menu();
    String users = homeMenu.user.toString();



    public interface  DataStatus {
        void DataIsLoaded(List<Machine_DB> books, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public Machine_Profile_Firebase() {

        mDatabase = FirebaseDatabase.getInstance();
        mReferenceBook = mDatabase.getReference("machineprofile").child(users);
    }
    public void  readBooks(final Machine_Profile_Firebase.DataStatus dataStatus){
        mReferenceBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Machine_DB i = keyNode.getValue(Machine_DB.class);
                    books.add(i);
                }
                dataStatus.DataIsLoaded(books ,keys);          }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void addBook(Machine_DB book, final Pet_Firebase.DataStatus dataStatus){

        String key = mReferenceBook.push().getKey();
        mReferenceBook.child(key).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }
}
