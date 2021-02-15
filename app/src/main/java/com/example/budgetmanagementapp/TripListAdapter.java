package com.example.budgetmanagementapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.ViewHolder> {
    ArrayList<TripAttr> tripAttrs;
    private Context context;
    Activity activity;

    public TripListAdapter(ArrayList<TripAttr> tripAttrs, Context context ,Activity activity) {
        this.context = context;
        this.tripAttrs = tripAttrs;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(tripAttrs.get(position).getTitle());
        String id = tripAttrs.get(position).getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity,Trips.class);
                intent.putExtra("id",id);
                activity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return tripAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtName);
        }
    }
}
