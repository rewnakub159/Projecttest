package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.crypto.Mac;

public class MacReConfig {
    private Context mcontext;
    private MacAdapter macAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<Machine_db> machine_dbs,List<String> keys){
        mcontext = context;
        macAdapter= new MacAdapter(machine_dbs,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(macAdapter);
    }


    class MacItemview extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mAmount;

        private String key;
        public MacItemview(ViewGroup parent) {
            super(LayoutInflater.from(mcontext).inflate(R.layout.book_list_item,parent,false));

            mName = (TextView)itemView.findViewById(R.id.tvname);
            mAmount = (TextView)itemView.findViewById(R.id.tvpass);
        }
        public void bind(Machine_db mac,String key)    {
            mName.setText(mac.getName());
            mAmount.setText(mac.getAmount_of_food());
            this.key = key;

        }
    }
        class MacAdapter extends RecyclerView.Adapter<MacItemview>{
            private List<Machine_db> maclist;
            private List<String> mkey;

            public MacAdapter(List<Machine_db> mlist, List<String> mkey) {
                this.maclist = maclist;
                this.mkey = mkey;
            }

            @NonNull
            @Override
            public MacItemview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull MacItemview holder, int position) {
                holder.bind(maclist.get(position),mkey.get(position));
            }

            @Override
            public int getItemCount() {
                return maclist.size();
            }
        }

}
