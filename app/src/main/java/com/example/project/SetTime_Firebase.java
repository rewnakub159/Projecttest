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

public class SetTime_Firebase {
      private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBook;
    private List<SetTime_Db> books = new ArrayList<>();

    LoginPage1 loginPage1 = new LoginPage1();
    String users = loginPage1.user.toString();



    public interface  DataStatus {
        void DataIsLoaded(List<SetTime_Db> books, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public SetTime_Firebase() {

        mDatabase = FirebaseDatabase.getInstance();
        mReferenceBook = mDatabase.getReference("time").child("fd001");
    }
    public void  readBooks(final SetTime_Firebase.DataStatus dataStatus){
        mReferenceBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    SetTime_Db i = keyNode.getValue(SetTime_Db.class);
                    books.add(i);
                }
                dataStatus.DataIsLoaded(books ,keys);          }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void addBook(SetTime_Db book, final DataStatus dataStatus){
        String d =book.getSettime();
        mReferenceBook.child(d).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }

}
