package com.example.budgetmanagementapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    ArrayList<notificationAttr> notificationAttrs;
    Activity context;

    public NotificationAdapter(ArrayList<notificationAttr> notificationAttrs, Activity context) {
        this.context = context;
        this.notificationAttrs = notificationAttrs;
    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifitem, parent, false);
        return new NotificationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder holder, final int position) {
        holder.desc.setText(notificationAttrs.get(position).getDescription());
        holder.datetime.setText(notificationAttrs.get(position).getTime());
        holder.title.setText(notificationAttrs.get(position).getTitle());

//        holder.itemView.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String latitude = addServiceAttrs.get(position).getLat();
//                final String longitude = addServiceAttrs.get(position).getLon();
//
//                Intent intent = new Intent( context, DirectionOnMap.class );
//                intent.putExtra("Latitude", latitude);
//                intent.putExtra("Longitude", longitude);
//                context.startActivity( intent );
//
//            }
//        } );

    }

    @Override
    public int getItemCount() {
        return notificationAttrs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView desc,datetime,title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.notification_title);
            desc = (TextView) itemView.findViewById(R.id.notification_description);
            datetime=(TextView) itemView.findViewById( R.id.notification_datetime );
        }
    }

}

