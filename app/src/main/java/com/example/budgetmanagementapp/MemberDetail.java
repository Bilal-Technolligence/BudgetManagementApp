package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MemberDetail extends BaseActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    ImageView profile;
    TextView name;
    Button call,msg;
    String contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_member_detail);
        String id = getIntent().getStringExtra("id");
        profile = findViewById(R.id.imgProfile);
        name = findViewById(R.id.txtName);
        call = findViewById(R.id.btnCall);
        msg = findViewById(R.id.btnMessage);
        if(!id.equals("")){
            databaseReference.child("Users").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        Picasso.get().load(dataSnapshot.child("imageUrl").getValue().toString()).into(profile);
                        name.setText(dataSnapshot.child("name").getValue().toString());
                        contact = dataSnapshot.child("contact").getValue().toString();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:" + contact);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", contact);
                smsIntent.putExtra("sms_body","");
                startActivity(smsIntent);
            }
        });

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_member_detail;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_trip;
    }
}