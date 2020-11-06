package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Pet_RecycleView_Config {
    private Context mContext;
    private  BooksAdapter mBookAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Pet_DB> books, List<String> keys){
        mContext = context;
        mBookAdapter = new BooksAdapter(books,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBookAdapter);
    }
    class BookItemView extends RecyclerView.ViewHolder{
        private String petnumber;
        private TextView petname;
        private TextView type;
        private TextView breed;
        private TextView gender;
        private String weight;
        private String age;
        private String key;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.pet_card,parent,false));

            petname = (TextView)itemView.findViewById(R.id.name);
            type = (TextView)itemView.findViewById(R.id.volume);
            breed = (TextView)itemView.findViewById(R.id.volume_level);
            gender = (TextView)itemView.findViewById(R.id.gender_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext,Pet_Profile.class);
                    intent.putExtra("petnumber",petnumber);
                    intent.putExtra("petname",petname.getText().toString());
                    intent.putExtra("type",type.getText().toString());
                    intent.putExtra("breed",breed.getText().toString());
                    intent.putExtra("gender",gender.getText().toString());
                    intent.putExtra("weight",weight);
                    intent.putExtra("age",age);
                    mContext.startActivity(intent);

                }
            });
        }
        public  void bind(Pet_DB book , String key){
            petnumber = book.getPetnumber();
            petname.setText(book.getPetname());
            type.setText(book.getType());
            breed.setText(book.getBreed());
            gender.setText(book.getGender());
            weight = book.getWeight();
            age = book.getAge();
            this.key = key;

        }
    }

    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Pet_DB>mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<Pet_DB> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.bind(mBookList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
