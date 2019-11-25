package com.example.recyclerviewstudentversion;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

// Todo Implement methods required
//onCreateViewHolder()
//onBindViewHolder
//getItemCount
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    protected static ArrayList<Player> list;
    protected LayoutInflater mInflater;

    protected MyRecyclerAdapter(Context context, ArrayList<Player> data) {
        this.mInflater = LayoutInflater.from(context);
        this.list = data;
    }

    @NonNull
    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.single_player, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.MyViewHolder holder, int position) {
        Player player = list.get(position);
        holder.name.setText(player.getName());
        holder.age.setText(player.getAge()+"");
        holder.sport.setText(player.getMainSport());
        holder.worth.setText(player.getWorth()+"");
        holder.imageView.setImageResource(player.getImageResource());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Todo implement ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // get references to each of the views in the single_item.xml
        // Todo implement constructor
        protected TextView name, age, worth, sport;
        protected ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            worth = itemView.findViewById(R.id.worth);
            sport = itemView.findViewById(R.id.sport);
            imageView = itemView.findViewById(R.id.picture);
        }
        
    }


}
