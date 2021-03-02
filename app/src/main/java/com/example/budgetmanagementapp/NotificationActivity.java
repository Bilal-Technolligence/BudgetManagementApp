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
import java.util.Collections;

public class NotificationActivity extends BaseActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    ArrayList<notificationAttr> pacakgeAttrs;
    NotificationAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_notification);

        recyclerView=findViewById(R.id.nList);
        pacakgeAttrs = new ArrayList<notificationAttr>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.child("ExpenseNoti").orderByChild("senderid").equalTo(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pacakgeAttrs.clear();
                //profiledata.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    notificationAttr p = dataSnapshot1.getValue(notificationAttr.class);
                    pacakgeAttrs.add(p);
                }
                Collections.reverse(pacakgeAttrs);
                recyclerView.setAdapter(new NotificationAdapter(pacakgeAttrs , NotificationActivity.this));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("ExpenseNoti").orderByChild("senderid").equalTo(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int a = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            databaseReference.child("ExpenseNoti").child(dataSnapshot1.getKey()).child("status").setValue("read");
                        }

                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_notification;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_notifications;
    }
}