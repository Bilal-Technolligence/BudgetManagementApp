package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class TripsList extends BaseActivity {
    RecyclerView tripList;
    ProgressDialog progressDialog;
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    String tripId = "";
    ArrayList<TripAttr> tripAttrs;
    Button createTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_trips_list);
        tripList = findViewById(R.id.recycler);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..... ");
        progressDialog.show();
        tripList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        tripAttrs = new ArrayList<TripAttr>();
        createTrip = findViewById(R.id.btnCreateTrip);
        createTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TripsList.this , CreateTrip.class));
            }
        });
        databaseReference.child("Trip").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        tripAttrs.clear();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            databaseReference.child("Trip").child(dataSnapshot1.getKey()).child("Members").child(uid).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        tripId = dataSnapshot1.getKey();
                                        TripAttr p = dataSnapshot1.getValue(TripAttr.class);
                                        tripAttrs.add(p);
                                    }
                                    Collections.reverse(tripAttrs);
                                    tripList.setAdapter(new TripListAdapter(tripAttrs, getApplicationContext() , TripsList.this));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }


                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Trip Not Found!", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                } catch (Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_trips_list;
    }

    @Override
    int getNavigationMenuItemId() {
        return  R.id.nav_trip;
    }
}