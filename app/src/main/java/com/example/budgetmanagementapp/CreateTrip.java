package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateTrip extends BaseActivity {
    CardView create;
    EditText title;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_trip);
        create = (CardView) findViewById(R.id.create);
        title = (EditText) findViewById(R.id.txtTripTitled);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title = title.getText().toString();
                if (Title.equals("")) {
                    title.setError("Enter Valid Title");
                    title.setFocusable(true);
                } else {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    reference.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {
                                if (dataSnapshot.exists()) {
                                    String name = dataSnapshot.child("name").getValue().toString();
                                    final String push = FirebaseDatabase.getInstance().getReference().child("Trip").push().getKey();
                                    reference.child("Trip").child(push).child("Title").setValue(Title);
                                    reference.child("Trip").child(push).child("Members").child(uid).child("id").setValue(uid);
                                    reference.child("Trip").child(push).child("Members").child(uid).child("name").setValue(name);
                                    Toast.makeText(getApplicationContext(), "Trip Created", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(CreateTrip.this, Trips.class));
                                    finish();
                                }
                            }
                            catch (Exception e){
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CreateTrip.this, Trips.class));
        finish();
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_create_trip;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_trip;
    }
}