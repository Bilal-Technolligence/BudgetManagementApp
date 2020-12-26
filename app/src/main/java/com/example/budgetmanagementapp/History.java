package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class History extends BaseActivity {
    RecyclerView expenseList, expenseList2;
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    ArrayList<ExpenseAttr> expenseAttrs;
    ExpenseListAdapter adapter;
    TextView previous;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_history);

        final Calendar cldr = Calendar.getInstance();
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        expenseList = findViewById(R.id.recycler);
        expenseList2 = findViewById(R.id.recycler2);
        previous = findViewById(R.id.txtPrevious);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..... ");
        progressDialog.show();
        expenseList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        expenseAttrs = new ArrayList<ExpenseAttr>();
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month + 1)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    expenseAttrs.clear();
                    //profiledata.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ExpenseAttr p = dataSnapshot1.getValue(ExpenseAttr.class);
                        expenseAttrs.add(p);
                    }
                    Collections.reverse(expenseAttrs);
                    expenseList.setAdapter(new ExpenseListAdapter(expenseAttrs, getApplicationContext()));
                } else
                    Toast.makeText(getApplicationContext(), "No expense Found", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    expenseAttrs.clear();
                    //profiledata.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ExpenseAttr p = dataSnapshot1.getValue(ExpenseAttr.class);
                        expenseAttrs.add(p);
                    }
                    Collections.reverse(expenseAttrs);
                    expenseList2.setAdapter(new ExpenseListAdapter(expenseAttrs, getApplicationContext()));
                } else {
                    Toast.makeText(getApplicationContext(), "No expense Found for previous month", Toast.LENGTH_LONG).show();
                    //previous.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        progressDialog.dismiss();
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_history;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_history;
    }
}