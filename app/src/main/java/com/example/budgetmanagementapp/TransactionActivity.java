package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
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
    TextView totalAmount;
    int total = 0;
    int abc=100;
    ProgressDialog progressDialog;
    BarChart barChart;
    String date;
    Spinner days;
    String dayD = "This month";
    int y1 = 0 , y2 = 0,y3= 0, y4=0, y5=0,y6=0, y7=0, y8=0,y9=0,y10=0, y11 = 0 , y12 = 0,y13= 0, y14=0, y15=0,y16=0, y17=0, y18=0,y19=0,y20=0, y21 = 0 , y22 = 0,y23= 0, y24=0, y25=0,y26=0, y27=0, y28=0,y29=0,y30=0 , y31=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_transaction);

        days = findViewById(R.id.days);
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
        days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayD = (String) parent.getItemAtPosition(position);
                if(!dayD.equals("This month")){
                    y1 = 0; y2 = 0;y3= 0;y4=0;y5=0;y6=0;y7=0;y8=0;y9=0;y10=0;y11 = 0 ;y12 = 0;y13= 0;y15=0;y16=0;y17=0;y18=0;y19=0;y20=0;y21 = 0 ;y22 = 0;y23= 0;y24=0;y25=0;y26=0;y27=0;y28=0;y29=0;y30=0 ;y31=0;
                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month )).addValueEventListener(new ValueEventListener() {
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
                                expenseAttrs.clear();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                total = 0;
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

                    progressDialog.dismiss();



                    //graph////


                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                try{
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                        date = dataSnapshot1.child("date").getValue().toString();
                                        String newDate = date.substring(0,2);
                                        if(newDate.equals("1-")){
                                            y1 = y1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("2-")){
                                            y2 = y2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("3-")){
                                            y3 = y3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("4-")){
                                            y4 = y4 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("5-")){
                                            y5 = y5 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("6-")){
                                            y6 = y6 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("7-")){
                                            y7 = y7 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("8-")){
                                            y8 = y8 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("9-")){
                                            y9 = y9 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("10")){
                                            y10 = y10 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("11")){
                                            y11 = y11 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("12")){
                                            y12 = y12 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("13")){
                                            y13 = y13 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("14")){
                                            y14 = y14 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("15")){
                                            y15 = y15 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("16")){
                                            y16 = y16 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("17")){
                                            y17 = y17 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("18")){
                                            y18 = y18 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("19")){
                                            y19 = y19 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("20")){
                                            y20 = y20 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("21")){
                                            y21 = y21 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("22")){
                                            y22 = y22 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("23")){
                                            y23 = y23 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("24")){
                                            y24 = y24 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("25")){
                                            y25 = y25 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("26")){
                                            y26 = y26 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("27")){
                                            y27 = y27 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("28")){
                                            y28 = y28 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("29")){
                                            y29 = y29 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("30")){
                                            y30 = y30 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("31")){
                                            y31 = y31 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
                                        dataVals.add( new BarEntry(1, Integer.valueOf(y1) ));
                                        dataVals.add( new BarEntry( 2, y2 ) );
                                        dataVals.add( new BarEntry( 3, y3 ) );
                                        dataVals.add( new BarEntry( 4, y4 ) );
                                        dataVals.add( new BarEntry( 5, y5 ) );
                                        dataVals.add( new BarEntry( 6, y6 ) );
                                        dataVals.add( new BarEntry( 7, y7 ) );
                                        dataVals.add( new BarEntry(8, y8 ));
                                        dataVals.add( new BarEntry( 9, y9 ) );
                                        dataVals.add( new BarEntry( 10, y10) );
                                        dataVals.add( new BarEntry( 11, y11 ) );
                                        dataVals.add( new BarEntry( 12, y12 ) );
                                        dataVals.add( new BarEntry( 13, y13 ) );
                                        dataVals.add( new BarEntry( 14, y14 ) );
                                        dataVals.add( new BarEntry( 15, y15 ) );
                                        dataVals.add( new BarEntry( 16, y16 ) );
                                        dataVals.add( new BarEntry(17, y17 ));
                                        dataVals.add( new BarEntry( 18, y18 ) );
                                        dataVals.add( new BarEntry( 19, y19 ) );
                                        dataVals.add( new BarEntry( 20, y20 ) );
                                        dataVals.add( new BarEntry( 21, y21 ) );
                                        dataVals.add( new BarEntry( 22, y22 ) );
                                        dataVals.add( new BarEntry( 23, y23 ) );
                                        dataVals.add( new BarEntry(24, y24 ));
                                        dataVals.add( new BarEntry( 25, y25 ) );
                                        dataVals.add( new BarEntry( 26, y26 ) );
                                        dataVals.add( new BarEntry( 27, y27 ) );
                                        dataVals.add( new BarEntry( 28, y28 ) );
                                        dataVals.add( new BarEntry( 29, y29 ) );
                                        dataVals.add( new BarEntry( 30, y30 ) );
                                        dataVals.add( new BarEntry( 31, y31 ) );
                                        BarDataSet barDataSet1 = new BarDataSet(dataVals,"Monthly Expense ");
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
                                    }
                                }
                                catch (Exception e){}
                            } else {
                                Toast.makeText(getApplicationContext(), "No expense Found", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
                                dataVals.add( new BarEntry(1, Integer.valueOf(y1) ));
                                dataVals.add( new BarEntry( 2, y2 ) );
                                dataVals.add( new BarEntry( 3, y3 ) );
                                dataVals.add( new BarEntry( 4, y4 ) );
                                dataVals.add( new BarEntry( 5, y5 ) );
                                dataVals.add( new BarEntry( 6, y6 ) );
                                dataVals.add( new BarEntry( 7, y7 ) );
                                dataVals.add( new BarEntry(8, y8 ));
                                dataVals.add( new BarEntry( 9, y9 ) );
                                dataVals.add( new BarEntry( 10, y10) );
                                dataVals.add( new BarEntry( 11, y11 ) );
                                dataVals.add( new BarEntry( 12, y12 ) );
                                dataVals.add( new BarEntry( 13, y13 ) );
                                dataVals.add( new BarEntry( 14, y14 ) );
                                dataVals.add( new BarEntry( 15, y15 ) );
                                dataVals.add( new BarEntry( 16, y16 ) );
                                dataVals.add( new BarEntry(17, y17 ));
                                dataVals.add( new BarEntry( 18, y18 ) );
                                dataVals.add( new BarEntry( 19, y19 ) );
                                dataVals.add( new BarEntry( 20, y20 ) );
                                dataVals.add( new BarEntry( 21, y21 ) );
                                dataVals.add( new BarEntry( 22, y22 ) );
                                dataVals.add( new BarEntry( 23, y23 ) );
                                dataVals.add( new BarEntry(24, y24 ));
                                dataVals.add( new BarEntry( 25, y25 ) );
                                dataVals.add( new BarEntry( 26, y26 ) );
                                dataVals.add( new BarEntry( 27, y27 ) );
                                dataVals.add( new BarEntry( 28, y28 ) );
                                dataVals.add( new BarEntry( 29, y29 ) );
                                dataVals.add( new BarEntry( 30, y30 ) );
                                dataVals.add( new BarEntry( 31, y31 ) );
                                BarDataSet barDataSet1 = new BarDataSet(dataVals,"Monthly Expense ");
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
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else {
                    y1 = 0; y2 = 0;y3= 0;y4=0;y5=0;y6=0;y7=0;y8=0;y9=0;y10=0;y11 = 0 ;y12 = 0;y13= 0;y15=0;y16=0;y17=0;y18=0;y19=0;y20=0;y21 = 0 ;y22 = 0;y23= 0;y24=0;y25=0;y26=0;y27=0;y28=0;y29=0;y30=0 ;y31=0;
                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1 )).addValueEventListener(new ValueEventListener() {
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
                                expenseAttrs.clear();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month +1)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                total = 0;
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

                    progressDialog.dismiss();



                    //graph////


                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month +1)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                try{
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                        date = dataSnapshot1.child("date").getValue().toString();
                                        String newDate = date.substring(0,2);
                                        if(newDate.equals("1-")){
                                            y1 = y1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("2-")){
                                            y2 = y2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("3-")){
                                            y3 = y3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("4-")){
                                            y4 = y4 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("5-")){
                                            y5 = y5 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("6-")){
                                            y6 = y6 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("7-")){
                                            y7 = y7 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("8-")){
                                            y8 = y8 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("9-")){
                                            y9 = y9 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("10")){
                                            y10 = y10 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("11")){
                                            y11 = y11 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("12")){
                                            y12 = y12 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("13")){
                                            y13 = y13 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("14")){
                                            y14 = y14 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("15")){
                                            y15 = y15 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("16")){
                                            y16 = y16 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("17")){
                                            y17 = y17 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("18")){
                                            y18 = y18 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("19")){
                                            y19 = y19 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("20")){
                                            y20 = y20 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("21")){
                                            y21 = y21 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("22")){
                                            y22 = y22 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("23")){
                                            y23 = y23 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("24")){
                                            y24 = y24 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("25")){
                                            y25 = y25 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("26")){
                                            y26 = y26 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("27")){
                                            y27 = y27 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("28")){
                                            y28 = y28 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("29")){
                                            y29 = y29 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("30")){
                                            y30 = y30 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        if(newDate.equals("31")){
                                            y31 = y31 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }
                                        ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
                                        dataVals.add( new BarEntry(1, Integer.valueOf(y1) ));
                                        dataVals.add( new BarEntry( 2, y2 ) );
                                        dataVals.add( new BarEntry( 3, y3 ) );
                                        dataVals.add( new BarEntry( 4, y4 ) );
                                        dataVals.add( new BarEntry( 5, y5 ) );
                                        dataVals.add( new BarEntry( 6, y6 ) );
                                        dataVals.add( new BarEntry( 7, y7 ) );
                                        dataVals.add( new BarEntry(8, y8 ));
                                        dataVals.add( new BarEntry( 9, y9 ) );
                                        dataVals.add( new BarEntry( 10, y10) );
                                        dataVals.add( new BarEntry( 11, y11 ) );
                                        dataVals.add( new BarEntry( 12, y12 ) );
                                        dataVals.add( new BarEntry( 13, y13 ) );
                                        dataVals.add( new BarEntry( 14, y14 ) );
                                        dataVals.add( new BarEntry( 15, y15 ) );
                                        dataVals.add( new BarEntry( 16, y16 ) );
                                        dataVals.add( new BarEntry(17, y17 ));
                                        dataVals.add( new BarEntry( 18, y18 ) );
                                        dataVals.add( new BarEntry( 19, y19 ) );
                                        dataVals.add( new BarEntry( 20, y20 ) );
                                        dataVals.add( new BarEntry( 21, y21 ) );
                                        dataVals.add( new BarEntry( 22, y22 ) );
                                        dataVals.add( new BarEntry( 23, y23 ) );
                                        dataVals.add( new BarEntry(24, y24 ));
                                        dataVals.add( new BarEntry( 25, y25 ) );
                                        dataVals.add( new BarEntry( 26, y26 ) );
                                        dataVals.add( new BarEntry( 27, y27 ) );
                                        dataVals.add( new BarEntry( 28, y28 ) );
                                        dataVals.add( new BarEntry( 29, y29 ) );
                                        dataVals.add( new BarEntry( 30, y30 ) );
                                        dataVals.add( new BarEntry( 31, y31 ) );
                                        BarDataSet barDataSet1 = new BarDataSet(dataVals,"Monthly Expense ");
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
                                    }
                                }
                                catch (Exception e){}
                            } else {
                                Toast.makeText(getApplicationContext(), "No expense Found", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
                                dataVals.add( new BarEntry(1, Integer.valueOf(y1) ));
                                dataVals.add( new BarEntry( 2, y2 ) );
                                dataVals.add( new BarEntry( 3, y3 ) );
                                dataVals.add( new BarEntry( 4, y4 ) );
                                dataVals.add( new BarEntry( 5, y5 ) );
                                dataVals.add( new BarEntry( 6, y6 ) );
                                dataVals.add( new BarEntry( 7, y7 ) );
                                dataVals.add( new BarEntry(8, y8 ));
                                dataVals.add( new BarEntry( 9, y9 ) );
                                dataVals.add( new BarEntry( 10, y10) );
                                dataVals.add( new BarEntry( 11, y11 ) );
                                dataVals.add( new BarEntry( 12, y12 ) );
                                dataVals.add( new BarEntry( 13, y13 ) );
                                dataVals.add( new BarEntry( 14, y14 ) );
                                dataVals.add( new BarEntry( 15, y15 ) );
                                dataVals.add( new BarEntry( 16, y16 ) );
                                dataVals.add( new BarEntry(17, y17 ));
                                dataVals.add( new BarEntry( 18, y18 ) );
                                dataVals.add( new BarEntry( 19, y19 ) );
                                dataVals.add( new BarEntry( 20, y20 ) );
                                dataVals.add( new BarEntry( 21, y21 ) );
                                dataVals.add( new BarEntry( 22, y22 ) );
                                dataVals.add( new BarEntry( 23, y23 ) );
                                dataVals.add( new BarEntry(24, y24 ));
                                dataVals.add( new BarEntry( 25, y25 ) );
                                dataVals.add( new BarEntry( 26, y26 ) );
                                dataVals.add( new BarEntry( 27, y27 ) );
                                dataVals.add( new BarEntry( 28, y28 ) );
                                dataVals.add( new BarEntry( 29, y29 ) );
                                dataVals.add( new BarEntry( 30, y30 ) );
                                dataVals.add( new BarEntry( 31, y31 ) );
                                BarDataSet barDataSet1 = new BarDataSet(dataVals,"Monthly Expense ");
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

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dayD = "This month";
            }
        });
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
                    total = 0;
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

        progressDialog.dismiss();



        //graph////

        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month + 1)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            date = dataSnapshot1.child("date").getValue().toString();
                            String newDate = date.substring(0,2);
                            if(newDate.equals("1-")){
                                y1 = y1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("2-")){
                                y2 = y2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("3-")){
                                y3 = y3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("4-")){
                                y4 = y4 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("5-")){
                                y5 = y5 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("6-")){
                                y6 = y6 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("7-")){
                                y7 = y7 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("8-")){
                                y8 = y8 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("9-")){
                                y9 = y9 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("10")){
                                y10 = y10 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("11")){
                                y11 = y11 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("12")){
                                y12 = y12 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("13")){
                                y13 = y13 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("14")){
                                y14 = y14 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("15")){
                                y15 = y15 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("16")){
                                y16 = y16 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("17")){
                                y17 = y17 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("18")){
                                y18 = y18 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("19")){
                                y19 = y19 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("20")){
                                y20 = y20 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("21")){
                                y21 = y21 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("22")){
                                y22 = y22 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("23")){
                                y23 = y23 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("24")){
                                y24 = y24 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("25")){
                                y25 = y25 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("26")){
                                y26 = y26 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("27")){
                                y27 = y27 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("28")){
                                y28 = y28 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("29")){
                                y29 = y29 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("30")){
                                y30 = y30 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("31")){
                                y31 = y31 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
                            dataVals.add( new BarEntry(1, Integer.valueOf(y1) ));
                            dataVals.add( new BarEntry( 2, y2 ) );
                            dataVals.add( new BarEntry( 3, y3 ) );
                            dataVals.add( new BarEntry( 4, y4 ) );
                            dataVals.add( new BarEntry( 5, y5 ) );
                            dataVals.add( new BarEntry( 6, y6 ) );
                            dataVals.add( new BarEntry( 7, y7 ) );
                            dataVals.add( new BarEntry(8, y8 ));
                            dataVals.add( new BarEntry( 9, y9 ) );
                            dataVals.add( new BarEntry( 10, y10) );
                            dataVals.add( new BarEntry( 11, y11 ) );
                            dataVals.add( new BarEntry( 12, y12 ) );
                            dataVals.add( new BarEntry( 13, y13 ) );
                            dataVals.add( new BarEntry( 14, y14 ) );
                            dataVals.add( new BarEntry( 15, y15 ) );
                            dataVals.add( new BarEntry( 16, y16 ) );
                            dataVals.add( new BarEntry(17, y17 ));
                            dataVals.add( new BarEntry( 18, y18 ) );
                            dataVals.add( new BarEntry( 19, y19 ) );
                            dataVals.add( new BarEntry( 20, y20 ) );
                            dataVals.add( new BarEntry( 21, y21 ) );
                            dataVals.add( new BarEntry( 22, y22 ) );
                            dataVals.add( new BarEntry( 23, y23 ) );
                            dataVals.add( new BarEntry(24, y24 ));
                            dataVals.add( new BarEntry( 25, y25 ) );
                            dataVals.add( new BarEntry( 26, y26 ) );
                            dataVals.add( new BarEntry( 27, y27 ) );
                            dataVals.add( new BarEntry( 28, y28 ) );
                            dataVals.add( new BarEntry( 29, y29 ) );
                            dataVals.add( new BarEntry( 30, y30 ) );
                            dataVals.add( new BarEntry( 31, y31 ) );
                            BarDataSet barDataSet1 = new BarDataSet(dataVals,"Monthly Expense ");
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
                        }
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



        return ;
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