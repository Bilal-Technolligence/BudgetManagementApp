package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class TransactionActivity extends BaseActivity {
    RecyclerView expenseList;
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    ArrayList<ExpenseAttr> expenseAttrs;
    TextView totalAmount;
    int total = 0;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_transaction);
        final Calendar cldr = Calendar.getInstance();
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        expenseList = findViewById(R.id.recycler);
        totalAmount = findViewById(R.id.txtTotal);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..... ");
        progressDialog.show();
        expenseList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        expenseAttrs = new ArrayList<ExpenseAttr>();
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month + 1)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                    expenseAttrs.clear();
                    //profiledata.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ExpenseAttr p = dataSnapshot1.getValue(ExpenseAttr.class);
                        expenseAttrs.add(p);
                    }
                    Collections.reverse(expenseAttrs);
                    expenseList.setAdapter(new ExpenseListAdapter(expenseAttrs, getApplicationContext()));
                    }
                    catch (Exception e){}
                    } else {
                    Toast.makeText(getApplicationContext(), "No expense Found", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month + 1)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            total = total + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        totalAmount.setText(String.valueOf(total));
                    }
                    catch (Exception e){}
                    } else totalAmount.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        GraphView graph = (GraphView) findViewById(R.id.graph);
        try {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                    new DataPoint(0, 0),
                    new DataPoint(Integer.valueOf(1), Integer.valueOf(10000)),
                    new DataPoint(Integer.valueOf(2), Integer.valueOf(20000)),
                    new DataPoint(Integer.valueOf(3), Integer.valueOf(30000)),
                    new DataPoint(Integer.valueOf(4), Integer.valueOf(40000)),
                    new DataPoint(Integer.valueOf(5), Integer.valueOf(10000)),
                    new DataPoint(Integer.valueOf(6), Integer.valueOf(20000)),
            });
            graph.addSeries(series);
        } catch (IllegalArgumentException e) {
            Toast.makeText(TransactionActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        progressDialog.dismiss();
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_transaction;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_transaction;
    }
}