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

public class Machine_Profile_con {
    private Context mContext;
    private Machine_Profile_con.BooksAdapter mBookAdapter;
    TextView tv1;
    public static String macname;
    public void setConfig(RecyclerView recyclerView, Context context, List<Machine_DB> books, List<String> keys){
        mContext = context;
        mBookAdapter = new Machine_Profile_con.BooksAdapter(books,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBookAdapter);
    }
    class BookItemView extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView volume_now;
        private TextView food_level;
        private String key;
        private String timeno;
        private String createdate;
        private String macnumber;



        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.selectmac_layout,parent,false));

            mName = (TextView)itemView.findViewById(R.id.name);
            volume_now = (TextView)itemView.findViewById(R.id.volume);
            food_level = (TextView)itemView.findViewById(R.id.volume_level);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext,Machine_profile.class);
                    intent.putExtra("macname",mName.getText().toString());
                    intent.putExtra("macnumber",macnumber);
                    intent.putExtra("volume_now",volume_now.getText().toString());
                    intent.putExtra("food_level",food_level.getText().toString());
                    intent.putExtra("timeno",timeno);
                    intent.putExtra("createdate",createdate);
                    mContext.startActivity(intent);

                }
            });
        }
        public  void bind(Machine_DB book , String key){
            mName.setText(book.getName());
            volume_now.setText(book.getVolume_now());
            food_level.setText(book.getFood_level());
            timeno = book.getTimeno();
            createdate = book.getCreatedate();
            macnumber = book.getMacnumber();
            this.key = key;

        }
    }
    class BooksAdapter extends RecyclerView.Adapter<Machine_Profile_con.BookItemView>{
        private List<Machine_DB>mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<Machine_DB> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public Machine_Profile_con.BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Machine_Profile_con.BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull Machine_Profile_con.BookItemView holder, int position) {
            holder.bind(mBookList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
