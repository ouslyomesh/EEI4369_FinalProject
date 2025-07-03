package com.s23010464.eei4369_finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList id_text, name_text, comment_text;


    CustomAdapter(Activity activity, Context context, ArrayList id_text, ArrayList name_text, ArrayList comment_text){
        this.activity = activity;
        this.context = context;
        this.id_text = id_text;
        this.name_text = name_text;
        this.comment_text = comment_text;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.review_id_text.setText(String.valueOf(id_text.get(position)));
        holder.review_name_text.setText(String.valueOf(name_text.get(position)));
        holder.review_comment_text.setText(String.valueOf(comment_text.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Update.class);
                intent.putExtra("id", String.valueOf(id_text.get(position)));
                intent.putExtra("name", String.valueOf(name_text.get(position)));
                intent.putExtra("comment", String.valueOf(comment_text.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_text.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView review_id_text, review_name_text, review_comment_text;
        LinearLayout mainLayout;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            review_id_text = itemView.findViewById(R.id.id_text);
            review_name_text = itemView.findViewById(R.id.name_text);
            review_comment_text = itemView.findViewById(R.id.comment_text);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
