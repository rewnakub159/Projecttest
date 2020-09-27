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

    Home_Menu loginPage1 = new Home_Menu();
    String users = loginPage1.user.toString();
    SelectMac_ReCon selectMac_reCon=new SelectMac_ReCon();
    String macname = SelectMac_ReCon.macname.toString();



    public interface  DataStatus {
        void DataIsLoaded(List<SetTime_Db> books, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public SetTime_Firebase() {

        mDatabase = FirebaseDatabase.getInstance();
        mReferenceBook = mDatabase.getReference("time").child(macname);
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
    public void updaeBook(String key, SetTime_Db book , final DataStatus dataStatus){
        mReferenceBook.child(key).setValue(book)
    .addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
           dataStatus.DataIsUpdated();
        }
    });
    }
    public  void deleteBook(String key,final DataStatus dataStatus){
        mReferenceBook.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });

    }
}
