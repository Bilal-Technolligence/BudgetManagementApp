package com.example.budgetmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddMember extends BaseActivity {
    EditText searchtext;
    ArrayList<UserAttr> userAttrs;
    String tripId;
    SearchListAdapter adapter;
    RecyclerView recyclerView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_member);
        Intent i = getIntent();
        tripId = i.getStringExtra("Trip");
        searchtext= findViewById(R.id.find);
        userAttrs = new ArrayList<UserAttr>();
        recyclerView=findViewById(R.id.searchList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initTextListener();
    }
    private void initTextListener() {
        userAttrs.clear();
        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = searchtext.getText().toString().toUpperCase();
                searchForMatch(text);
            }

        });
    }

    private void searchForMatch(String text) {
        userAttrs.clear();
        updatePostList();
        if(text.length() ==0)
        {
            return;
        }

        else
        {

            Query query = FirebaseDatabase.getInstance().getReference("Users")
                    .orderByChild("name")
                    .startAt(text)
                    .endAt(text+"\uf8ff");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    userAttrs.clear();
                    for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren())
                    {
                        if(!userAttrs.contains(singleSnapshot.getValue(UserAttr.class)))
                        {
                            String user = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                            if(!user.equals(singleSnapshot.child("id").getValue().toString()))
                                userAttrs.add(singleSnapshot.getValue(UserAttr.class));
                        }

                    }
                    try{
                        updatePostList();
                    }
                    catch (Exception ex)
                    {

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
    }

    private void updatePostList() {
        recyclerView.setAdapter(new SearchListAdapter(userAttrs , getApplicationContext() , AddMember.this , tripId ));

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddMember.this ,Trips.class ));
        finish();
    }
    @Override
    int getContentViewId() {
        return R.layout.activity_add_member;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_trip;
    }
}