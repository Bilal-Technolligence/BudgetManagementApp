package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    ArrayList<notificationAttr> pacakgeAttrs;
    NotificationAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView=findViewById(R.id.nList);
        pacakgeAttrs = new ArrayList<notificationAttr>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.child("ExpenseNoti").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pacakgeAttrs.clear();
                //profiledata.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    notificationAttr p = dataSnapshot1.getValue(notificationAttr.class);
                    pacakgeAttrs.add(p);
                }

                recyclerView.setAdapter(new NotificationAdapter(pacakgeAttrs , NotificationActivity.this));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}