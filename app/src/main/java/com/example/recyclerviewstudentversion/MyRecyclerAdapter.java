package com.example.recyclerviewstudentversion;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> implements Filterable {

    protected static ArrayList<Player> list;
    protected static ArrayList<Player> listFull;
    private Player lastRemoved = null;

    public void removeFromList(int position) {
        lastRemoved = list.remove(position);
        notifyItemRemoved(position);
    }

    public boolean swapPositions(int viewHolder, int target) {
        list.set(target, list.set(viewHolder, list.get(target)));
        notifyItemMoved(viewHolder, target);
        return true;
    }

    protected MyRecyclerAdapter(Context context, ArrayList<Player> data) {
        this.list = data;
        listFull = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_player, parent, false);
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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Player> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(listFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Player player : listFull) {
                    if (player.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(player);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list.clear();
            list.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };

    // Todo implement ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // get references to each of the views in the single_item.xml
        // Todo implement constructor
        protected TextView name, age, worth, sport;
        protected ImageView imageView;
        protected LinearLayout linearLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.my_recycler_view);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            worth = itemView.findViewById(R.id.worth);
            sport = itemView.findViewById(R.id.sport);
            imageView = itemView.findViewById(R.id.picture);
        }
        
    }


}
