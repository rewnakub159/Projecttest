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

public class Pet_Firebase {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBook;
    private List<Pet_DB> books = new ArrayList<>();

    Home_Menu loginPage1 = new Home_Menu();
    String users = loginPage1.user.toString();




    public interface  DataStatus {
        void DataIsLoaded(List<Pet_DB> books, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public Pet_Firebase() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceBook = mDatabase.getReference("pet").child(users);
    }
    public void  readBooks(final Pet_Firebase.DataStatus dataStatus){
        mReferenceBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Pet_DB i = keyNode.getValue(Pet_DB.class);
                    if (i.getPetname().equals("null")){
                    }else { books.add(i);
                    }
                }
                dataStatus.DataIsLoaded(books ,keys);          }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void addBook(Pet_DB book, final Pet_Firebase.DataStatus dataStatus){
        String d ="1";
        //String d = mReferenceBook.push().getKey();
        mReferenceBook.child(d).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }
    public void updaeBook(String key, Pet_DB book , final Pet_Firebase.DataStatus dataStatus){
        mReferenceBook.child(key).setValue(book)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }
    public  void deleteBook(String key,final Pet_Firebase.DataStatus dataStatus){
        mReferenceBook.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });

    }
}
