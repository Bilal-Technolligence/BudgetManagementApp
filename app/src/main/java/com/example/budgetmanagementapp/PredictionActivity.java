package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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

public class PredictionActivity extends BaseActivity {
    LineChart lineChartTotal,lineChartFuel ,lineChartFood , lineChartShopping , lineChartKids ,lineChartClothes , lineChartGift ,lineChartSport , lineChartEntertainment ,lineChartOther;
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    String date;
    TextView total , totalT , fuel ,fuelT , food, foodT,shop , shopT, kids, kidsT, cloth , clothT , gift , giftT , sport ,sportT , ent , entT , other , othetT;
    int to= 0, toT= 0 , fu= 0 ,fuT= 0 , fo= 0 , foT= 0 ,sh= 0  , shT= 0 , ki= 0 , kiT= 0 , cl= 0  , clT= 0  , gi = 0 , giT = 0 , sp = 0 ,spT = 0 , en= 0  , enT = 0 , ot= 0  , otT= 0 ;

    int t1 = 0 , t2 = 0,t3= 0;
    int f1 = 0 , f2 = 0,f3= 0;
    int fo1 = 0 , fo2 = 0,fo3= 0;
    int s1 = 0 , s2 = 0,s3= 0;
    int k1 = 0 , k2 = 0,k3= 0;
    int c1 = 0 , c2 = 0,c3= 0;
    int g1 = 0 , g2 = 0,g3= 0;
    int sp1 = 0 , sp2 = 0,sp3= 0;
    int e1 = 0 , e2 = 0,e3= 0;
    int o1 = 0 , o2 = 0,o3= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_prediction);
        lineChartTotal = (LineChart) findViewById(R.id.graphTotal);
        lineChartFuel = (LineChart) findViewById(R.id.graphFuel);
        lineChartFood = (LineChart) findViewById(R.id.graphFood);
        lineChartShopping = (LineChart) findViewById(R.id.graphShopping);
        lineChartKids = (LineChart) findViewById(R.id.graphKids);
        lineChartClothes = (LineChart) findViewById(R.id.graphClothes);
        lineChartGift = (LineChart) findViewById(R.id.graphGift);
        lineChartSport = (LineChart) findViewById(R.id.graphSports);
        lineChartEntertainment = (LineChart) findViewById(R.id.graphEntertainment);
        lineChartOther = (LineChart) findViewById(R.id.graphOther);
        total = findViewById(R.id.txtTotal);
        totalT = findViewById(R.id.txtTotalThis);
        fuel = findViewById(R.id.txtFuel);
        fuelT = findViewById(R.id.txtFuelThis);
        food = findViewById(R.id.txtFood);
        foodT = findViewById(R.id.txtFoodThis);
        shop = findViewById(R.id.txtShopping);
        shopT = findViewById(R.id.txtShoppingThis);
        kids = findViewById(R.id.txtKids);
        kidsT = findViewById(R.id.txtkidsThis);
        cloth = findViewById(R.id.txtClothes);
        clothT = findViewById(R.id.txtClothesThis);
        gift = findViewById(R.id.txtGift);
        giftT = findViewById(R.id.txtGiftThis);
        sport = findViewById(R.id.txtSports);
        sportT = findViewById(R.id.txtSportsThis);
        ent = findViewById(R.id.txtEntertainment);
        entT = findViewById(R.id.txtEntertainmentThis);
        other = findViewById(R.id.txtOther);
        othetT = findViewById(R.id.txtOtherThis);


        final Calendar cldr = Calendar.getInstance();
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            date = dataSnapshot1.child("date").getValue().toString();
                            String newDate = date.substring(0,2);
                            if(newDate.equals("1-")){
                                t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("2-")){
                                t1 = t1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("3-")){
                                t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("4-")){
                                t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("5-")){
                                t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("6-")){
                                t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("7-")){
                                t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("8-")){
                                t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("9-")){
                                t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("10")){
                                t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("11")){
                                t2 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("12")){
                                t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("13")){
                                t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("14")){
                                t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("15")){
                                t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("16")){
                                t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("17")){
                                t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("18")){
                                t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("19")){
                                t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("20")){
                                t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("21")){
                                t3 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("22")){
                                t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("23")){
                                t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("24")){
                                t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("25")){
                                t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("26")){
                                t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("27")){
                                t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("28")){
                                t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("29")){
                                t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("30")){
                                t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("31")){
                                t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            ArrayList<Entry> dataValsTotal = new ArrayList<>();
                            dataValsTotal.add(new Entry(0 , 0));
                            dataValsTotal.add( new Entry(10, t1 ));
                            dataValsTotal.add( new Entry( 20, t2 ) );
                            dataValsTotal.add( new Entry( 30, t3 ) );
                            databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        t1= 0;
                                        t2 = 0;
                                        t3= 0;
                                        try{
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                date = dataSnapshot1.child("date").getValue().toString();
                                                String newDate = date.substring(0,2);
                                                if(newDate.equals("1-")){
                                                    t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("2-")){
                                                    t1 = t1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("3-")){
                                                    t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("4-")){
                                                    t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("5-")){
                                                    t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("6-")){
                                                    t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("7-")){
                                                    t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("8-")){
                                                    t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("9-")){
                                                    t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("10")){
                                                    t1 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("11")){
                                                    t2 = t1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("12")){
                                                    t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("13")){
                                                    t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("14")){
                                                    t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("15")){
                                                    t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("16")){
                                                    t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("17")){
                                                    t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("18")){
                                                    t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("19")){
                                                    t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("20")){
                                                    t2 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("21")){
                                                    t3 = t2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("22")){
                                                    t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("23")){
                                                    t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("24")){
                                                    t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("25")){
                                                    t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("26")){
                                                    t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("27")){
                                                    t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("28")){
                                                    t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("29")){
                                                    t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("30")){
                                                    t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("31")){
                                                    t3 = t3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                ArrayList<Entry> dataval2this = new ArrayList<>();
                                                dataval2this.add(new Entry(0 , 0));
                                                dataval2this.add(new Entry(10 , t1));
                                                if(t2!=0)
                                                    dataval2this.add(new Entry(20 , t2));
                                                if(t3!=0)
                                                    dataval2this.add(new Entry(30 , t3));
                                                LineDataSet lineDataSet = new LineDataSet(dataValsTotal, "Expected");
                                                lineDataSet.setColor(Color.GREEN);
                                                LineDataSet lineDataSet2 = new LineDataSet(dataval2this, "This Month");
                                                lineDataSet2.setColor(Color.RED);
                                                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                                dataSets.add(lineDataSet);
                                                dataSets.add(lineDataSet2);
                                                LineData data = new LineData(dataSets);
                                                lineChartTotal.setData(data);
                                                lineChartTotal.invalidate();
                                            }
                                        }
                                        catch (Exception e){}
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Fuel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            date = dataSnapshot1.child("date").getValue().toString();
                            String newDate = date.substring(0,2);
                            if(newDate.equals("1-")){
                                f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("2-")){
                                f1 = f1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("3-")){
                                f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("4-")){
                                f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("5-")){
                                f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("6-")){
                                f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("7-")){
                                f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("8-")){
                                f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("9-")){
                                f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("10")){
                                f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("11")){
                                f2 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("12")){
                                f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("13")){
                                f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("14")){
                                f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("15")){
                                f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("16")){
                                f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("17")){
                                f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("18")){
                                f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("19")){
                                f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("20")){
                                f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("21")){
                                f3 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("22")){
                                f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("23")){
                                f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("24")){
                                f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("25")){
                                f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("26")){
                                f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("27")){
                                f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("28")){
                                f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("29")){
                                f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("30")){
                                f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("31")){
                                f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            ArrayList<Entry> dataVals = new ArrayList<>();
                            dataVals.add(new Entry(0 , 0));
                            dataVals.add( new Entry(10, f1 ));
                            dataVals.add( new Entry( 20, f2 ) );
                            dataVals.add( new Entry( 30, f3 ) );
                            databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Fuel").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        f1= 0;
                                        f2 = 0;
                                        f3= 0;
                                        try{
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                date = dataSnapshot1.child("date").getValue().toString();
                                                String newDate = date.substring(0,2);
                                                if(newDate.equals("1-")){
                                                    f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("2-")){
                                                    f1 = f1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("3-")){
                                                    f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("4-")){
                                                    f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("5-")){
                                                    f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("6-")){
                                                    f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("7-")){
                                                    f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("8-")){
                                                    f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("9-")){
                                                    f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("10")){
                                                    f1 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("11")){
                                                    f2 = f1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("12")){
                                                    f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("13")){
                                                    f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("14")){
                                                    f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("15")){
                                                    f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("16")){
                                                    f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("17")){
                                                    f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("18")){
                                                    f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("19")){
                                                    f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("20")){
                                                    f2 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("21")){
                                                    f3 = f2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("22")){
                                                    f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("23")){
                                                    f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("24")){
                                                    f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("25")){
                                                    f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("26")){
                                                    f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("27")){
                                                    f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("28")){
                                                    f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("29")){
                                                    f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("30")){
                                                    f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("31")){
                                                    f3 = f3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                ArrayList<Entry> dataval2 = new ArrayList<>();
                                                dataval2.add(new Entry(0 , 0));
                                                dataval2.add(new Entry(10 , f1));
                                                if(f2!=0)
                                                dataval2.add(new Entry(20 , f2));
                                                if(f3!=0)
                                                dataval2.add(new Entry(30 , f3));
                                                LineDataSet lineDataSet = new LineDataSet(dataVals, "Expected");
                                                lineDataSet.setColor(Color.GREEN);
                                                LineDataSet lineDataSet2 = new LineDataSet(dataval2, "This Month");
                                                lineDataSet2.setColor(Color.RED);
                                                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                                dataSets.add(lineDataSet);
                                                dataSets.add(lineDataSet2);
                                                LineData data = new LineData(dataSets);
                                                lineChartFuel.setData(data);
                                                lineChartFuel.invalidate();
                                            }
                                        }
                                        catch (Exception e){}
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Food").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            date = dataSnapshot1.child("date").getValue().toString();
                            String newDate = date.substring(0,2);
                            if(newDate.equals("1-")){
                                fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("2-")){
                                fo1 = fo1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("3-")){
                                fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("4-")){
                                fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("5-")){
                                fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("6-")){
                                fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("7-")){
                                fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("8-")){
                                fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("9-")){
                                fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("10")){
                                fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("11")){
                                fo2 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("12")){
                                fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("13")){
                                fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("14")){
                                fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("15")){
                                fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("16")){
                                fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("17")){
                                fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("18")){
                                fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("19")){
                                fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("20")){
                                fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("21")){
                                fo3 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("22")){
                                fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("23")){
                                fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("24")){
                                fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("25")){
                                fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("26")){
                                fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("27")){
                                fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("28")){
                                fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("29")){
                                fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("30")){
                                fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("31")){
                                fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            ArrayList<Entry> dataVals = new ArrayList<>();
                            dataVals.add(new Entry(0 , 0));
                            dataVals.add( new Entry(10, fo1 ));
                            dataVals.add( new Entry( 20, fo2 ) );
                            dataVals.add( new Entry( 30, fo3 ) );
                            databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Food").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        fo1= 0;
                                        fo2 = 0;
                                        fo3= 0;
                                        try{
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                date = dataSnapshot1.child("date").getValue().toString();
                                                String newDate = date.substring(0,2);
                                                if(newDate.equals("1-")){
                                                    fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("2-")){
                                                    fo1 = fo1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("3-")){
                                                    fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("4-")){
                                                    fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("5-")){
                                                    fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("6-")){
                                                    fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("7-")){
                                                    fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("8-")){
                                                    fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("9-")){
                                                    fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("10")){
                                                    fo1 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("11")){
                                                    fo2 = fo1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("12")){
                                                    fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("13")){
                                                    fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("14")){
                                                    fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("15")){
                                                    fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("16")){
                                                    fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("17")){
                                                    fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("18")){
                                                    fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("19")){
                                                    fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("20")){
                                                    fo2 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("21")){
                                                    fo3 = fo2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("22")){
                                                    fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("23")){
                                                    fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("24")){
                                                    fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("25")){
                                                    fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("26")){
                                                    fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("27")){
                                                    fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("28")){
                                                    fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("29")){
                                                    fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("30")){
                                                    fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("31")){
                                                    fo3 = fo3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                ArrayList<Entry> dataval2 = new ArrayList<>();
                                                dataval2.add(new Entry(0 , 0));
                                                dataval2.add(new Entry(10 , fo1));
                                                if(fo2!=0)
                                                    dataval2.add(new Entry(20 , fo2));
                                                if(fo3!=0)
                                                    dataval2.add(new Entry(30 , fo3));
                                                LineDataSet lineDataSet = new LineDataSet(dataVals, "Expected");
                                                lineDataSet.setColor(Color.GREEN);
                                                LineDataSet lineDataSet2 = new LineDataSet(dataval2, "This Month");
                                                lineDataSet2.setColor(Color.RED);
                                                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                                dataSets.add(lineDataSet);
                                                dataSets.add(lineDataSet2);
                                                LineData data = new LineData(dataSets);
                                                lineChartFood.setData(data);
                                                lineChartFood.invalidate();
                                            }
                                        }
                                        catch (Exception e){}
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Shopping").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            date = dataSnapshot1.child("date").getValue().toString();
                            String newDate = date.substring(0,2);
                            if(newDate.equals("1-")){
                                s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("2-")){
                                s1 = s1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("3-")){
                                s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("4-")){
                                s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("5-")){
                                s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("6-")){
                                s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("7-")){
                                s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("8-")){
                                s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("9-")){
                                s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("10")){
                                s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("11")){
                                s2 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("12")){
                                s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("13")){
                                s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("14")){
                                s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("15")){
                                s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("16")){
                                s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("17")){
                                s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("18")){
                                s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("19")){
                                s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("20")){
                                s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("21")){
                                s3 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("22")){
                                s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("23")){
                                s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("24")){
                                s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("25")){
                                s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("26")){
                                s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("27")){
                                s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("28")){
                                s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("29")){
                                s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("30")){
                                s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("31")){
                                s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            ArrayList<Entry> dataVals = new ArrayList<>();
                            dataVals.add(new Entry(0 , 0));
                            dataVals.add( new Entry(10, s1 ));
                            dataVals.add( new Entry( 20, s2 ) );
                            dataVals.add( new Entry( 30, s3 ) );
                            databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Shopping").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        s1= 0;
                                        s2 = 0;
                                        s3= 0;
                                        try{
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                date = dataSnapshot1.child("date").getValue().toString();
                                                String newDate = date.substring(0,2);
                                                if(newDate.equals("1-")){
                                                    s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("2-")){
                                                    s1 = s1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("3-")){
                                                    s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("4-")){
                                                    s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("5-")){
                                                    s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("6-")){
                                                    s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("7-")){
                                                    s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("8-")){
                                                    s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("9-")){
                                                    s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("10")){
                                                    s1 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("11")){
                                                    s2 = s1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("12")){
                                                    s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("13")){
                                                    s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("14")){
                                                    s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("15")){
                                                    s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("16")){
                                                    s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("17")){
                                                    s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("18")){
                                                    s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("19")){
                                                    s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("20")){
                                                    s2 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("21")){
                                                    s3 = s2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("22")){
                                                    s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("23")){
                                                    s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("24")){
                                                    s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("25")){
                                                    s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("26")){
                                                    s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("27")){
                                                    s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("28")){
                                                    s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("29")){
                                                    s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("30")){
                                                    s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("31")){
                                                    s3 = s3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                ArrayList<Entry> dataval2 = new ArrayList<>();
                                                dataval2.add(new Entry(0 , 0));
                                                dataval2.add(new Entry(10 , s1));
                                                if(s2!=0)
                                                    dataval2.add(new Entry(20 , s2));
                                                if(s3!=0)
                                                    dataval2.add(new Entry(30 , s3));
                                                LineDataSet lineDataSet = new LineDataSet(dataVals, "Expected");
                                                lineDataSet.setColor(Color.GREEN);
                                                LineDataSet lineDataSet2 = new LineDataSet(dataval2, "This Month");
                                                lineDataSet2.setColor(Color.RED);
                                                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                                dataSets.add(lineDataSet);
                                                dataSets.add(lineDataSet2);
                                                LineData data = new LineData(dataSets);
                                                lineChartShopping.setData(data);
                                                lineChartShopping.invalidate();
                                            }
                                        }
                                        catch (Exception e){}
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Sports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            date = dataSnapshot1.child("date").getValue().toString();
                            String newDate = date.substring(0,2);
                            if(newDate.equals("1-")){
                                sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("2-")){
                                sp1 = sp1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("3-")){
                                sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("4-")){
                                sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("5-")){
                                sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("6-")){
                                sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("7-")){
                                sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("8-")){
                                sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("9-")){
                                sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("10")){
                                sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("11")){
                                sp2 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("12")){
                                sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("13")){
                                sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("14")){
                                sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("15")){
                                sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("16")){
                                sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("17")){
                                sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("18")){
                                sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("19")){
                                sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("20")){
                                sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("21")){
                                sp3 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("22")){
                                sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("23")){
                                sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("24")){
                                sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("25")){
                                sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("26")){
                                sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("27")){
                                sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("28")){
                                sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("29")){
                                sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("30")){
                                sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("31")){
                                sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            ArrayList<Entry> dataVals = new ArrayList<>();
                            dataVals.add(new Entry(0 , 0));
                            dataVals.add( new Entry(10, sp1 ));
                            dataVals.add( new Entry( 20, sp2 ) );
                            dataVals.add( new Entry( 30, sp3 ) );
                            databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Sports").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        sp1= 0;
                                        sp2 = 0;
                                        sp3= 0;
                                        try{
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                date = dataSnapshot1.child("date").getValue().toString();
                                                String newDate = date.substring(0,2);
                                                if(newDate.equals("1-")){
                                                    sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("2-")){
                                                    sp1 = sp1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("3-")){
                                                    sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("4-")){
                                                    sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("5-")){
                                                    sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("6-")){
                                                    sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("7-")){
                                                    sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("8-")){
                                                    sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("9-")){
                                                    sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("10")){
                                                    sp1 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("11")){
                                                    sp2 = sp1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("12")){
                                                    sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("13")){
                                                    sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("14")){
                                                    sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("15")){
                                                    sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("16")){
                                                    sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("17")){
                                                    sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("18")){
                                                    sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("19")){
                                                    sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("20")){
                                                    sp2 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("21")){
                                                    sp3 = sp2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("22")){
                                                    sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("23")){
                                                    sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("24")){
                                                    sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("25")){
                                                    sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("26")){
                                                    sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("27")){
                                                    sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("28")){
                                                    sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("29")){
                                                    sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("30")){
                                                    sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("31")){
                                                    sp3 = sp3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                ArrayList<Entry> dataval2 = new ArrayList<>();
                                                dataval2.add(new Entry(0 , 0));
                                                dataval2.add(new Entry(10 , sp1));
                                                if(sp2!=0)
                                                    dataval2.add(new Entry(20 , sp2));
                                                if(sp3!=0)
                                                    dataval2.add(new Entry(30 , sp3));
                                                LineDataSet lineDataSet = new LineDataSet(dataVals, "Expected");
                                                lineDataSet.setColor(Color.GREEN);
                                                LineDataSet lineDataSet2 = new LineDataSet(dataval2, "This Month");
                                                lineDataSet2.setColor(Color.RED);
                                                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                                dataSets.add(lineDataSet);
                                                dataSets.add(lineDataSet2);
                                                LineData data = new LineData(dataSets);
                                                lineChartSport.setData(data);
                                                lineChartSport.invalidate();
                                            }
                                        }
                                        catch (Exception e){}
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Kids").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            date = dataSnapshot1.child("date").getValue().toString();
                            String newDate = date.substring(0,2);
                            if(newDate.equals("1-")){
                                k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("2-")){
                                k1 = k1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("3-")){
                                k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("4-")){
                                k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("5-")){
                                k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("6-")){
                                k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("7-")){
                                k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("8-")){
                                k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("9-")){
                                k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("10")){
                                k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("11")){
                                k2 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("12")){
                                k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("13")){
                                k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("14")){
                                k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("15")){
                                k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("16")){
                                k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("17")){
                                k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("18")){
                                k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("19")){
                                k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("20")){
                                k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("21")){
                                k3 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("22")){
                                k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("23")){
                                k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("24")){
                                k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("25")){
                                k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("26")){
                                k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("27")){
                                k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("28")){
                                k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("29")){
                                k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("30")){
                                k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("31")){
                                k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            ArrayList<Entry> dataVals = new ArrayList<>();
                            dataVals.add(new Entry(0 , 0));
                            dataVals.add( new Entry(10, k1 ));
                            dataVals.add( new Entry( 20, k2 ) );
                            dataVals.add( new Entry( 30, k3 ) );
                            databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Kids").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        k1= 0;
                                        k2 = 0;
                                        k3= 0;
                                        try{
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                date = dataSnapshot1.child("date").getValue().toString();
                                                String newDate = date.substring(0,2);
                                                if(newDate.equals("1-")){
                                                    k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("2-")){
                                                    k1 = k1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("3-")){
                                                    k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("4-")){
                                                    k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("5-")){
                                                    k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("6-")){
                                                    k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("7-")){
                                                    k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("8-")){
                                                    k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("9-")){
                                                    k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("10")){
                                                    k1 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("11")){
                                                    k2 = k1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("12")){
                                                    k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("13")){
                                                    k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("14")){
                                                    k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("15")){
                                                    k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("16")){
                                                    k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("17")){
                                                    k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("18")){
                                                    k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("19")){
                                                    k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("20")){
                                                    k2 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("21")){
                                                    k3 = k2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("22")){
                                                    k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("23")){
                                                    k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("24")){
                                                    k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("25")){
                                                    k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("26")){
                                                    k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("27")){
                                                    k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("28")){
                                                    k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("29")){
                                                    k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("30")){
                                                    k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("31")){
                                                    k3 = k3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                ArrayList<Entry> dataval2 = new ArrayList<>();
                                                dataval2.add(new Entry(0 , 0));
                                                dataval2.add(new Entry(10 , k1));
                                                if(k2!=0)
                                                    dataval2.add(new Entry(20 , k2));
                                                if(k3!=0)
                                                    dataval2.add(new Entry(30 , k3));
                                                LineDataSet lineDataSet = new LineDataSet(dataVals, "Expected");
                                                lineDataSet.setColor(Color.GREEN);
                                                LineDataSet lineDataSet2 = new LineDataSet(dataval2, "This Month");
                                                lineDataSet2.setColor(Color.RED);
                                                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                                dataSets.add(lineDataSet);
                                                dataSets.add(lineDataSet2);
                                                LineData data = new LineData(dataSets);
                                                lineChartKids.setData(data);
                                                lineChartKids.invalidate();
                                            }
                                        }
                                        catch (Exception e){}
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Clothes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            date = dataSnapshot1.child("date").getValue().toString();
                            String newDate = date.substring(0,2);
                            if(newDate.equals("1-")){
                                c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("2-")){
                                c1 = c1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("3-")){
                                c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("4-")){
                                c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("5-")){
                                c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("6-")){
                                c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("7-")){
                                c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("8-")){
                                c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("9-")){
                                c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("10")){
                                c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("11")){
                                c2 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("12")){
                                c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("13")){
                                c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("14")){
                                c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("15")){
                                c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("16")){
                                c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("17")){
                                c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("18")){
                                c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("19")){
                                c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("20")){
                                c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("21")){
                                c3 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("22")){
                                c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("23")){
                                c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("24")){
                                c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("25")){
                                c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("26")){
                                c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("27")){
                                c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("28")){
                                c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("29")){
                                c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("30")){
                                c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("31")){
                                c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            ArrayList<Entry> dataVals = new ArrayList<>();
                            dataVals.add(new Entry(0 , 0));
                            dataVals.add( new Entry(10, c1 ));
                            dataVals.add( new Entry( 20, c2 ) );
                            dataVals.add( new Entry( 30, c3 ) );
                            databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Clothes").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        c1= 0;
                                        c2 = 0;
                                        c3= 0;
                                        try{
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                date = dataSnapshot1.child("date").getValue().toString();
                                                String newDate = date.substring(0,2);
                                                if(newDate.equals("1-")){
                                                    c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("2-")){
                                                    c1 = c1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("3-")){
                                                    c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("4-")){
                                                    c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("5-")){
                                                    c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("6-")){
                                                    c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("7-")){
                                                    c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("8-")){
                                                    c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("9-")){
                                                    c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("10")){
                                                    c1 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("11")){
                                                    c2 = c1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("12")){
                                                    c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("13")){
                                                    c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("14")){
                                                    c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("15")){
                                                    c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("16")){
                                                    c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("17")){
                                                    c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("18")){
                                                    c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("19")){
                                                    c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("20")){
                                                    c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("21")){
                                                    c3 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("22")){
                                                    c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("23")){
                                                    c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("24")){
                                                    c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("25")){
                                                    c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("26")){
                                                    c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("27")){
                                                    c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("28")){
                                                    c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("29")){
                                                    c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("30")){
                                                    c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("31")){
                                                    c3 = c3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                ArrayList<Entry> dataval2 = new ArrayList<>();
                                                dataval2.add(new Entry(0 , 0));
                                                dataval2.add(new Entry(10 , c1));
                                                if(c2!=0)
                                                    dataval2.add(new Entry(20 , c2));
                                                if(c3!=0)
                                                    dataval2.add(new Entry(30 , c3));
                                                LineDataSet lineDataSet = new LineDataSet(dataVals, "Expected");
                                                lineDataSet.setColor(Color.GREEN);
                                                LineDataSet lineDataSet2 = new LineDataSet(dataval2, "This Month");
                                                lineDataSet2.setColor(Color.RED);
                                                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                                dataSets.add(lineDataSet);
                                                dataSets.add(lineDataSet2);
                                                LineData data = new LineData(dataSets);
                                                lineChartClothes.setData(data);
                                                lineChartClothes.invalidate();
                                            }
                                        }
                                        catch (Exception e){}
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Gift").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            date = dataSnapshot1.child("date").getValue().toString();
                            String newDate = date.substring(0,2);
                            if(newDate.equals("1-")){
                                g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("2-")){
                                g1 = g1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("3-")){
                                g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("4-")){
                                g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("5-")){
                                g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("6-")){
                                g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("7-")){
                                g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("8-")){
                                g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("9-")){
                                g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("10")){
                                g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("11")){
                                g2 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("12")){
                                g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("13")){
                                g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("14")){
                                g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("15")){
                                c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("16")){
                                g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("17")){
                                g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("18")){
                                g2 = g2+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("19")){
                                g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("20")){
                                g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("21")){
                                g3 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("22")){
                                g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("23")){
                                g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("24")){
                                g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("25")){
                                g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("26")){
                                g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("27")){
                                g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("28")){
                                g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("29")){
                                g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("30")){
                                g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("31")){
                                g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            ArrayList<Entry> dataVals = new ArrayList<>();
                            dataVals.add(new Entry(0 , 0));
                            dataVals.add( new Entry(10, g1 ));
                            dataVals.add( new Entry( 20, g2 ) );
                            dataVals.add( new Entry( 30, g3 ) );
                            databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Gift").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        g1= 0;
                                        g2 = 0;
                                        g3= 0;
                                        try{
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                date = dataSnapshot1.child("date").getValue().toString();
                                                String newDate = date.substring(0,2);
                                                if(newDate.equals("1-")){
                                                    g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("2-")){
                                                    g1 = g1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("3-")){
                                                    g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("4-")){
                                                    g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("5-")){
                                                    g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("6-")){
                                                    g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("7-")){
                                                    g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("8-")){
                                                    g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("9-")){
                                                    g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("10")){
                                                    g1 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("11")){
                                                    g2 = g1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("12")){
                                                    g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("13")){
                                                    g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("14")){
                                                    g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("15")){
                                                    c2 = c2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("16")){
                                                    g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("17")){
                                                    g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("18")){
                                                    g2 = g2+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("19")){
                                                    g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("20")){
                                                    g2 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("21")){
                                                    g3 = g2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("22")){
                                                    g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("23")){
                                                    g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("24")){
                                                    g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("25")){
                                                    g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("26")){
                                                    g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("27")){
                                                    g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("28")){
                                                    g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("29")){
                                                    g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("30")){
                                                    g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("31")){
                                                    g3 = g3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                ArrayList<Entry> dataval2 = new ArrayList<>();
                                                dataval2.add(new Entry(0 , 0));
                                                dataval2.add(new Entry(10 , g1));
                                                if(g2!=0)
                                                    dataval2.add(new Entry(20 , g2));
                                                if(g3!=0)
                                                    dataval2.add(new Entry(30 , g3));
                                                LineDataSet lineDataSet = new LineDataSet(dataVals, "Expected");
                                                lineDataSet.setColor(Color.GREEN);
                                                LineDataSet lineDataSet2 = new LineDataSet(dataval2, "This Month");
                                                lineDataSet2.setColor(Color.RED);
                                                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                                dataSets.add(lineDataSet);
                                                dataSets.add(lineDataSet2);
                                                LineData data = new LineData(dataSets);
                                                lineChartGift.setData(data);
                                                lineChartGift.invalidate();
                                            }
                                        }
                                        catch (Exception e){}
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Entertainment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            date = dataSnapshot1.child("date").getValue().toString();
                            String newDate = date.substring(0,2);
                            if(newDate.equals("1-")){
                                e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("2-")){
                                e1 = e1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("3-")){
                                e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("4-")){
                                e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("5-")){
                                e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("6-")){
                                e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("7-")){
                                e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("8-")){
                                e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("9-")){
                                e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("10")){
                                e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("11")){
                                e2 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("12")){
                                e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("13")){
                                e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("14")){
                                e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("15")){
                                e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("16")){
                                e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("17")){
                                e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("18")){
                                e2 = e2+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("19")){
                                e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("20")){
                                e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("21")){
                                e3 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("22")){
                                e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("23")){
                                e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("24")){
                                e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("25")){
                                e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("26")){
                                e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("27")){
                                e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("28")){
                                e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("29")){
                                e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("30")){
                                e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("31")){
                                e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            ArrayList<Entry> dataVals = new ArrayList<>();
                            dataVals.add(new Entry(0 , 0));
                            dataVals.add( new Entry(10, e1 ));
                            dataVals.add( new Entry( 20, e2 ) );
                            dataVals.add( new Entry( 30, e3 ) );
                            databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Entertainment").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        e1= 0;
                                        e2 = 0;
                                        e3= 0;
                                        try{
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                date = dataSnapshot1.child("date").getValue().toString();
                                                String newDate = date.substring(0,2);
                                                if(newDate.equals("1-")){
                                                    e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("2-")){
                                                    e1 = e1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("3-")){
                                                    e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("4-")){
                                                    e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("5-")){
                                                    e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("6-")){
                                                    e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("7-")){
                                                    e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("8-")){
                                                    e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("9-")){
                                                    e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("10")){
                                                    e1 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("11")){
                                                    e2 = e1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("12")){
                                                    e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("13")){
                                                    e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("14")){
                                                    e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("15")){
                                                    e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("16")){
                                                    e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("17")){
                                                    e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("18")){
                                                    e2 = e2+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("19")){
                                                    e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("20")){
                                                    e2 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("21")){
                                                    e3 = e2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("22")){
                                                    e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("23")){
                                                    e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("24")){
                                                    e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("25")){
                                                    e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("26")){
                                                    e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("27")){
                                                    e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("28")){
                                                    e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("29")){
                                                    e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("30")){
                                                    e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("31")){
                                                    e3 = e3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                ArrayList<Entry> dataval2 = new ArrayList<>();
                                                dataval2.add(new Entry(0 , 0));
                                                dataval2.add(new Entry(10 , e1));
                                                if(e2!=0)
                                                    dataval2.add(new Entry(20 , e2));
                                                if(e3!=0)
                                                    dataval2.add(new Entry(30 , e3));
                                                LineDataSet lineDataSet = new LineDataSet(dataVals, "Expected");
                                                lineDataSet.setColor(Color.GREEN);
                                                LineDataSet lineDataSet2 = new LineDataSet(dataval2, "This Month");
                                                lineDataSet2.setColor(Color.RED);
                                                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                                dataSets.add(lineDataSet);
                                                dataSets.add(lineDataSet2);
                                                LineData data = new LineData(dataSets);
                                                lineChartEntertainment.setData(data);
                                                lineChartEntertainment.invalidate();
                                            }
                                        }
                                        catch (Exception e){}
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Other").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try{
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            date = dataSnapshot1.child("date").getValue().toString();
                            String newDate = date.substring(0,2);
                            if(newDate.equals("1-")){
                                o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("2-")){
                                o1 = o1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("3-")){
                                o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("4-")){
                                o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("5-")){
                                o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("6-")){
                                o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("7-")){
                                o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("8-")){
                                o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("9-")){
                                o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("10")){
                                o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("11")){
                                o2 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("12")){
                                o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("13")){
                                o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("14")){
                                o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("15")){
                                o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("16")){
                                o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("17")){
                                o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("18")){
                                o2 = o2+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("19")){
                                o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("20")){
                                o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("21")){
                                o3 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("22")){
                                o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("23")){
                                o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("24")){
                                o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("25")){
                                o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("26")){
                                o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("27")){
                                o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("28")){
                                o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("29")){
                                o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("30")){
                                o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            if(newDate.equals("31")){
                                o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                            ArrayList<Entry> dataVals = new ArrayList<>();
                            dataVals.add(new Entry(0 , 0));
                            dataVals.add( new Entry(10, o1 ));
                            dataVals.add( new Entry( 20, o2 ) );
                            dataVals.add( new Entry( 30, o3 ) );
                            databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Other").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        o1= 0;
                                        o2 = 0;
                                        o3= 0;
                                        try{
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                date = dataSnapshot1.child("date").getValue().toString();
                                                String newDate = date.substring(0,2);
                                                if(newDate.equals("1-")){
                                                    o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("2-")){
                                                    o1 = o1+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("3-")){
                                                    o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("4-")){
                                                    o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("5-")){
                                                    o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("6-")){
                                                    o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("7-")){
                                                    o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("8-")){
                                                    o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("9-")){
                                                    o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("10")){
                                                    o1 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("11")){
                                                    o2 = o1 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("12")){
                                                    o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("13")){
                                                    o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("14")){
                                                    o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("15")){
                                                    o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("16")){
                                                    o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("17")){
                                                    o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("18")){
                                                    o2 = o2+ Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("19")){
                                                    o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("20")){
                                                    o2 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("21")){
                                                    o3 = o2 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("22")){
                                                    o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("23")){
                                                    o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("24")){
                                                    o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("25")){
                                                    o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("26")){
                                                    o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("27")){
                                                    o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("28")){
                                                    o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("29")){
                                                    o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("30")){
                                                    o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                if(newDate.equals("31")){
                                                    o3 = o3 + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                                }
                                                ArrayList<Entry> dataval2 = new ArrayList<>();
                                                dataval2.add(new Entry(0 , 0));
                                                dataval2.add(new Entry(10 , o1));
                                                if(o2!=0)
                                                    dataval2.add(new Entry(20 , o2));
                                                if(o3!=0)
                                                    dataval2.add(new Entry(30 , o3));
                                                LineDataSet lineDataSet = new LineDataSet(dataVals, "Expected");
                                                lineDataSet.setColor(Color.GREEN);
                                                LineDataSet lineDataSet2 = new LineDataSet(dataval2, "This Month");
                                                lineDataSet2.setColor(Color.RED);
                                                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                                dataSets.add(lineDataSet);
                                                dataSets.add(lineDataSet2);
                                                LineData data = new LineData(dataSets);
                                                lineChartOther.setData(data);
                                                lineChartOther.invalidate();
                                            }
                                        }
                                        catch (Exception e){}
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    catch (Exception e){}
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
                    to = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            to = to + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        total.setText(String.valueOf(to));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else total.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    toT = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            toT = toT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        totalT.setText(String.valueOf(toT));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else totalT.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Fuel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    fu = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            fu = fu + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        fuel.setText(String.valueOf(fu));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else fuel.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Fuel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    fuT = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            fuT = fuT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        fuelT.setText(String.valueOf(fuT));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else fuelT.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Food").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    fo = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            fo = fo + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        fuel.setText(String.valueOf(fo));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else food.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Food").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    foT = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            foT = foT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        foodT.setText(String.valueOf(foT));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else foodT.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Shopping").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    sh = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            sh = sh + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        shop.setText(String.valueOf(sh));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else shop.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Shopping").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    shT = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            shT = shT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        shopT.setText(String.valueOf(shT));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else shopT.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Sports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    sp = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            sp = sp + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        sport.setText(String.valueOf(sp));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else sport.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Sports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    spT = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            spT = spT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        sportT.setText(String.valueOf(spT));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else sportT.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Kids").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ki = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            ki = ki + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        kids.setText(String.valueOf(ki));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else kids.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("kids").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    kiT = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            kiT = kiT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        kidsT.setText(String.valueOf(kiT));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else kidsT.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Clothes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    cl = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            cl = cl + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        cloth.setText(String.valueOf(cl));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else cloth.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Clothes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    clT = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            clT = clT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        clothT.setText(String.valueOf(clT));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else clothT.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Gift").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    gi = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            gi = gi + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        gift.setText(String.valueOf(gi));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else gift.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Gift").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    giT = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            giT = giT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        giftT.setText(String.valueOf(giT));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else giftT.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Entertainment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    en = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            en = en + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        ent.setText(String.valueOf(en));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else ent.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Entertainment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    enT = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            enT = enT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        entT.setText(String.valueOf(enT));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else entT.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month)).orderByChild("category").equalTo("Other").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ot = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            ot = ot + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        other.setText(String.valueOf(ot));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else other.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).orderByChild("category").equalTo("Other").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    otT = 0;
                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            otT = otT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        othetT.setText(String.valueOf(otT));
                        // abc =total;
                    }
                    catch (Exception e){}
                } else othetT.setText("0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_prediction;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_prediction;
    }
}