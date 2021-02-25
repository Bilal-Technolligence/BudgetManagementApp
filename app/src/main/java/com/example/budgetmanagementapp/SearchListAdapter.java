package com.example.budgetmanagementapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        try {
            Picasso.get().load(userAttrs.get(position).getImageUrl()).into(holder.profileImage);
        } catch (Exception e) {
            String firstLetter = String.valueOf(userAttrs.get(position).getName().charAt(0));
            ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
            int color = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(firstLetter, color); // radius in px
            holder.profileImage.setImageDrawable(drawable);
        }

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
                        final String push = FirebaseDatabase.getInstance().getReference().child("Notification").push().getKey();

                        databaseReference.child("ExpenseNoti").child(push).child("description").setValue("Your'e added in a shared budget!");
                        databaseReference.child("ExpenseNoti").child(push).child("status").setValue("unread");
                        databaseReference.child("ExpenseNoti").child(push).child("title").setValue("Budget Alert");
                        databaseReference.child("ExpenseNoti").child(push).child("senderid").setValue(Id);
                        databaseReference.child("ExpenseNoti").child(push).child("id").setValue(push);

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
