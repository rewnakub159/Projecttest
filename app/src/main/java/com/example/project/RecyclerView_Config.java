package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private  BooksAdapter mBookAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Register_DB> books, List<String> keys){
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

            mName = (TextView)itemView.findViewById(R.id.name);
            mPass = (TextView)itemView.findViewById(R.id.volume);
            mEmail = (TextView)itemView.findViewById(R.id.volume_level);
        }
        public void bind(Register_DB book , String key){
            mName.setText(book.getUsername());
            mPass.setText(book.getPassword());
            mEmail.setText(book.getEmail());
            this.key = key;
        }
    }
    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Register_DB>mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<Register_DB> mBookList, List<String> mKeys) {
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
