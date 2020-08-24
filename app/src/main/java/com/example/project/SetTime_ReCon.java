package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SetTime_ReCon {
    private Context mContext;
    private SetTime_ReCon.BooksAdapter mBookAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<SetTime_Db> books, List<String> keys){
        mContext = context;
        mBookAdapter = new SetTime_ReCon.BooksAdapter(books,keys);
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

            mName = (TextView)itemView.findViewById(R.id.tvname);
            mPass = (TextView)itemView.findViewById(R.id.tvpass);
            mEmail = (TextView)itemView.findViewById(R.id.tvemail);
        }
        public  void bind(SetTime_Db book , String key){
            mName.setText(book.getName());
            mPass.setText(book.getAmountfood());
            mEmail.setText(book.getSettime());
            this.key = key;

        }
    }

    class BooksAdapter extends RecyclerView.Adapter<SetTime_ReCon.BookItemView>{
        private List<SetTime_Db>mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<SetTime_Db> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public SetTime_ReCon.BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SetTime_ReCon.BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SetTime_ReCon.BookItemView holder, int position) {
            holder.bind(mBookList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
