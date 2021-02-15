package com.example.budgetmanagementapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchListAdapter extends RecyclerView.Adapter<com.example.budgetmanagementapp.SearchListAdapter.ViewHolder> {
    ArrayList<UserAttr> userAttrs;
    private Context context;
    Activity AddMember;
    String tripId;

    public SearchListAdapter(ArrayList<UserAttr> userAttrs, Context context, AddMember addMember, String tripId) {
        this.context = context;
        this.userAttrs = userAttrs;
        this.AddMember = addMember;
        this.tripId = tripId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.get().load(userAttrs.get(position).getImageUrl()).into(holder.profileImage);
        holder.name.setText(userAttrs.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Id = userAttrs.get(position).getId();
                //Toast.makeText(context , Id , Toast.LENGTH_SHORT).show();

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddMember);
                alertDialogBuilder.setMessage("Are you sure to add in trip?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference();
                        databaseReference.child("Trip").child(tripId).child("Members").child(Id).child("id").setValue(Id);
                        databaseReference.child("Trip").child(tripId).child("Members").child(Id).child("name").setValue(userAttrs.get(position).getName());
                        Toast.makeText(context, "Member added", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Intent intent = new Intent(AddMember, Trips.class);
                        intent.putExtra("id", tripId);
                        AddMember.startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return userAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.imgProfile);
            name = itemView.findViewById(R.id.txtName);


        }
    }
}
