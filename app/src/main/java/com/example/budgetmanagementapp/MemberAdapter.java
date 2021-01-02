package com.example.budgetmanagementapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
    ArrayList<UserAttr> userAttrs;
    private Context context;
    Activity trips;
    String tripId;
    String admin;

    public MemberAdapter(ArrayList<UserAttr> userAttrs, Context context, Trips trips, String tripId, String admin) {
        this.context = context;
        this.trips = trips;
        this.userAttrs = userAttrs;
        this.tripId = tripId;
        this.admin = admin;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memberlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(admin.equals("no"))
            holder.deleteBtn.setVisibility(View.GONE);
        holder.name.setText(userAttrs.get(position).getName());

        final String id = userAttrs.get(position).getId();
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(trips);
                alertDialogBuilder.setMessage("Are you sure to remove from trip?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference();
                        databaseReference.child("Trip").child(tripId).child("Members").child(id).setValue(null);
                        dialog.dismiss();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

//                Intent i = new Intent(context , ServiceDetail.class);
//                i.putExtra("Id" , serviceId);
//                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView deleteBtn;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteBtn = (ImageView) itemView.findViewById(R.id.imgDelete);
            name = (TextView) itemView.findViewById(R.id.txtName);


        }
    }
}
