package com.example.project.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.History_DB;
import com.example.project.R;
import com.example.project.SetTime_Re1;

import java.util.List;

public class History_ReCon {

    private Context mContext;
    private History_ReCon.BooksAdapter mBookAdapter;
    TextView tv1;
    public static String macname;
    public void setConfig(RecyclerView recyclerView, Context context, List<History_DB> books, List<String> keys){
        mContext = context;
        mBookAdapter = new History_ReCon.BooksAdapter(books,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBookAdapter);
    }
    class BookItemView extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mPass;
        private TextView mEmail;

        private String key;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.selectmac_layout,parent,false));

            mName = (TextView)itemView.findViewById(R.id.name);
            mPass = (TextView)itemView.findViewById(R.id.volume);
            mEmail = (TextView)itemView.findViewById(R.id.volume_level);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, SetTime_Re1.class);
                    macname = mName.getText().toString();
                    mContext.startActivity(intent);

                }
            });
        }
        public  void bind(History_DB book , String key){
            mName.setText(book.getId());
            mPass.setText(book.getVolume());
            mEmail.setText(book.getDate());
            this.key = key;
        }
    }
    class BooksAdapter extends RecyclerView.Adapter<History_ReCon.BookItemView>{
        private List<History_DB>mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<History_DB> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public History_ReCon.BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new History_ReCon.BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull History_ReCon.BookItemView holder, int position) {
            holder.bind(mBookList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
