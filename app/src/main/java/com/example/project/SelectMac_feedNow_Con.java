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

public class SelectMac_feedNow_Con {

    private Context mContext;
    private SelectMac_feedNow_Con.BooksAdapter mBookAdapter;
    TextView tv1;
    public static String macname;
    public void setConfig(RecyclerView recyclerView, Context context, List<Machine_DB> books, List<String> keys){
        mContext = context;
        mBookAdapter = new SelectMac_feedNow_Con.BooksAdapter(books,keys);
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

            mName = (TextView)itemView.findViewById(R.id.petname);
            mPass = (TextView)itemView.findViewById(R.id.volume_tv);
            mEmail = (TextView)itemView.findViewById(R.id.breed_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, Feeding_now.class);
                    macname = mName.getText().toString();
                    mContext.startActivity(intent);

                }
            });
        }
        public  void bind(Machine_DB book , String key){
            mName.setText(book.getName());
            mPass.setText(book.getVolume());
            mEmail.setText(book.getFood_level());
            this.key = key;

        }
    }
    class BooksAdapter extends RecyclerView.Adapter<SelectMac_feedNow_Con.BookItemView>{
        private List<Machine_DB>mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<Machine_DB> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public SelectMac_feedNow_Con.BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SelectMac_feedNow_Con.BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SelectMac_feedNow_Con.BookItemView holder, int position) {
            holder.bind(mBookList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
