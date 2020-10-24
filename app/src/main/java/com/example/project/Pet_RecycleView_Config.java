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
        private TextView name;
        private TextView type;
        private TextView breed;
        private TextView gender;

        private String key;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.pet_card,parent,false));

            name = (TextView)itemView.findViewById(R.id.petname);
            type = (TextView)itemView.findViewById(R.id.volume_tv);
            breed = (TextView)itemView.findViewById(R.id.breed_tv);
            gender = (TextView)itemView.findViewById(R.id.gender_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext,Pet_Profile.class);

                    mContext.startActivity(intent);

                }
            });
        }
        public  void bind(Pet_DB book , String key){
            name.setText(book.getPetname());
            type.setText(book.getType());
            breed.setText(book.getBreed());
            gender.setText(book.getGender());
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
