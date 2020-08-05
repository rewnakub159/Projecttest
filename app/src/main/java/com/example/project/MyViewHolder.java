package com.example.project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder  extends RecyclerView.ViewHolder {

    TextView username , password;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        username=itemView.findViewById(R.id.textView3);
        password=itemView.findViewById(R.id.textView5);
    }
}
