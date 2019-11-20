package com.example.gamifiedassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.ItemViewHolder> {
    private ArrayList<Country> countriesToAdapt;
    private Context context;

    public LearnAdapter(ArrayList<Country> countriesToAdapt){
        this.countriesToAdapt= countriesToAdapt;
        this.context = context;
    }

    public void setData(ArrayList<Country> countriesToAdapt) {
       this.countriesToAdapt = countriesToAdapt;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.country_entry, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;


    }

    //passes country and navigates detail activity
    @Override
     public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final Country countryAtPosition = countriesToAdapt.get(position);

        holder.nameTV.setText(countryAtPosition.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, LearnDetailActivity.class);
                intent.putExtra("CountryID", countryAtPosition.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return countriesToAdapt.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView nameTV;
        public ImageView imageIV;

        public ItemViewHolder(View v) {
            super(v);
            view = v.findViewById(R.id.country_view);
            nameTV = v.findViewById(R.id.country_text);
            imageIV = v.findViewById(R.id.country_image);
        }


    }
}
