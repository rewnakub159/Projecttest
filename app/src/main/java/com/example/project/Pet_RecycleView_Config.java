package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
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
        private TextView mName;
        private TextView mPass;
        private TextView mEmail;

        private String key;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.book_list_item,parent,false));

            mName = (TextView)itemView.findViewById(R.id.macname_tv1);
            mPass = (TextView)itemView.findViewById(R.id.volumn_tv);
            mEmail = (TextView)itemView.findViewById(R.id.volumnnow_tv);
        }
        public  void bind(Pet_DB book , String key){
            mName.setText(book.getName());
            mPass.setText(book.getAge());
            mEmail.setText(book.getType());
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
