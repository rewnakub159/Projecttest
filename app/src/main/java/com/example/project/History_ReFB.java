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

public class History_ReFB {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBook;
    private List<History_DB> books = new ArrayList<>();

    Home_Menu homeMenu = new Home_Menu();
    String users = homeMenu.user.toString();

    Machine_profile machine_profile = new Machine_profile();
    String mac = machine_profile.mac.toString();



    public interface  DataStatus {
        void DataIsLoaded(List<History_DB> books, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public History_ReFB() {

        mDatabase = FirebaseDatabase.getInstance();
        mReferenceBook = mDatabase.getReference("history").child(mac);
    }
    public void  readBooks(final History_ReFB.DataStatus dataStatus){
        mReferenceBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    History_DB i = keyNode.getValue(History_DB.class);
                    if (i.getId().equals("null")){
                    }else { books.add(i);
                    }
                }
                dataStatus.DataIsLoaded(books ,keys);          }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void addBook(History_DB book, final Pet_Firebase.DataStatus dataStatus){

        String key = mReferenceBook.push().getKey();
        mReferenceBook.child(key).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }
    public void updaeBook(String key, History_DB book , final History_ReFB.DataStatus dataStatus){
        mReferenceBook.child(key).setValue(book)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }
}
