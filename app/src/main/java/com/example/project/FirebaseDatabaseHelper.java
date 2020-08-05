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

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBook;
    private List<Register_db> books = new ArrayList<>();

    public interface  DataStatus {
        void DataIsLoaded(List<Register_db> books,List<String> keys);
        void DataIsserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceBook = mDatabase.getReference("user");
    }
    public void  readBooks(final DataStatus dataStatus){
        mReferenceBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keynode :dataSnapshot.getChildren()){
                    keys.add(keynode.getKey());
                    Register_db book = keynode.getValue(Register_db.class);
                    books.add(book);
                }
                dataStatus.DataIsLoaded(books ,keys);          }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void addBook(Register_db book,final DataStatus dataStatus){
       String key = mReferenceBook.push().getKey();
       mReferenceBook.child(key).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               dataStatus.DataIsserted();
           }
       });
    }
}
