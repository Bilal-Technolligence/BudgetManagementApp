package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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
    ExpenseListAdapter adapter;
    TextView totalAmount;
    int total = 0;
    int abc=100;
    ProgressDialog progressDialog;
    BarChart barChart;

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
                       // abc =total;
                    }
                    catch (Exception e){}
                    } else totalAmount.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



     //   GraphView graph = (GraphView) findViewById(R.id.graph);
//        try {
//            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
//                    new DataPoint(0, 0),
//                    new DataPoint(Integer.valueOf(1), Integer.valueOf(10000)),
//                    new DataPoint(Integer.valueOf(2), Integer.valueOf(20000)),
//                    new DataPoint(Integer.valueOf(3), Integer.valueOf(30000)),
//                    new DataPoint(Integer.valueOf(4), Integer.valueOf(40000)),
//                    new DataPoint(Integer.valueOf(5), Integer.valueOf(10000)),
//                    new DataPoint(Integer.valueOf(6), Integer.valueOf(20000)),
//            });
//            graph.addSeries(series);
//        } catch (IllegalArgumentException e) {
//            Toast.makeText(TransactionActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
        progressDialog.dismiss();



        //graph////
        barChart = (BarChart) findViewById(R.id.bargraph);

        YAxis leftAxis = barChart.getAxisLeft();
        YAxis rightAxis = barChart.getAxisRight();
        XAxis xAxis = barChart.getXAxis();

        xAxis.setPosition( XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12f);
        xAxis.setDrawAxisLine(true);
        xAxis.setLabelCount(7);
        xAxis.setDrawGridLines(false);


        leftAxis.setTextSize(12f);
        leftAxis.setDrawLabels(true);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawGridLines(true);

        rightAxis.setDrawAxisLine(true);
        rightAxis.setDrawGridLines(true);
        rightAxis.setDrawLabels(true);


       // int abc=total;
        // int abc=Integer.parseInt(xyz);
        // String abc=txtUsed.getText();
        BarDataSet barDataSet1 = new BarDataSet(dataValues1(abc),"Monthly Expense ");

        BarData barData = new BarData();
        barData.addDataSet(barDataSet1);
        barData.setBarWidth(0.3f); // set custom bar width
        barChart.setData(barData);
        barChart.setData(barData);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate();
        barChart.setScaleEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setBackgroundColor(Color.rgb(255, 255, 255));
        barChart.animateXY(2000, 2000);
        barChart.setDrawBorders(false);
        //   barChart.setDescription(desc);
        barChart.setDrawValueAboveBar(true);
        barData.notifyDataChanged(); // let the data know a dataSet changed
        barDataSet1.notifyDataSetChanged();
        return ;
    }


    ///Graph Values
    private ArrayList<BarEntry> dataValues1(int abc) {
        ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
        dataVals.add( new BarEntry(1, abc ));
        dataVals.add( new BarEntry( 2, 7 ) );
        dataVals.add( new BarEntry( 3, 9 ) );
        dataVals.add( new BarEntry( 4, 12 ) );
        dataVals.add( new BarEntry( 5, 75 ) );
        dataVals.add( new BarEntry( 6, 50 ) );
        dataVals.add( new BarEntry( 7, 15 ) );

        return dataVals;
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