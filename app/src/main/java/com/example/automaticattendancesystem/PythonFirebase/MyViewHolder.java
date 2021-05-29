package com.example.automaticattendancesystem.PythonFirebase;



import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automaticattendancesystem.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView mName;TextView mLink;
    Button mDownload;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        mName=itemView.findViewById(R.id.name);
        mLink=itemView.findViewById(R.id.link);
        mDownload=itemView.findViewById(R.id.down);



    }
}
