package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trips extends BaseActivity {
    LinearLayout view, view2;
    ProgressDialog progressDialog;
    Button addMember, addExpense, endTrip, createTrip ,exitTrip ,pay;
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    String tripId = "";
    TextView tripTitle , p;
    RecyclerView expenseList, memberList, paymentList;
    ArrayList<ExpenseAttr> expenseAttrs;
    ArrayList<UserAttr> userAttrs;
    ArrayList<PaymentAttr> paymentAttrs;
    ExpenseListAdapter adapter;
    TextView food, fuel, sports, entertainment , budget;
    int  fuelT = 0 , sportsT = 0,entertainmentT= 0, foodT=0;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_trips);
        view = findViewById(R.id.view);
        view2 = findViewById(R.id.view2);
        addMember = findViewById(R.id.btnAddMember);
        addExpense = findViewById(R.id.btnAddExpense);
        endTrip = findViewById(R.id.btnEndtrip);
        createTrip = findViewById(R.id.btnCreateTrip);
        pay = findViewById(R.id.btnPayExpense);
        exitTrip = findViewById(R.id.btnExitTrip);
        expenseList = findViewById(R.id.recyclerExpense);
        memberList = findViewById(R.id.recycler);
        paymentList = findViewById(R.id.recyclerPayments);
        tripTitle = findViewById(R.id.txtTrip);
        budget = findViewById(R.id.txtBudget);
        fuel = (TextView) findViewById(R.id.txtFuel);
        sports = (TextView) findViewById(R.id.txtSports);
        p = (TextView) findViewById(R.id.txtpay);
        entertainment = (TextView) findViewById(R.id.txtEntertainment);
        food = (TextView) findViewById(R.id.txtFood);
        exitTrip.setVisibility(View.GONE);
        createTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Trips.this, CreateTrip.class));
                finish();
            }
        });


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..... ");
        progressDialog.show();
        view.setVisibility(View.GONE);
        view2.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
        //Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_SHORT).show();
        tripId = getIntent().getStringExtra("id");
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Trips.this,PaymentActivity.class);
                intent.putExtra("Trip",tripId);
                startActivity(intent);
                finish();
            }
        });
        if(tripId!=null){
            databaseReference.child("Trip").child(tripId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        if (dataSnapshot.exists()) {
                            tripTitle.setText(dataSnapshot.child("Title").getValue().toString());
                            budget.setText(dataSnapshot.child("Budget").getValue().toString());
                            String budget = dataSnapshot.child("Budget").getValue().toString();
                            Integer b = Integer.parseInt(budget);
                        databaseReference.child("Trip").child(tripId).child("Expense").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    try {
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                            total = total + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                        }

                                       if(total>=b){

//                                           Intent notificationIntent = new Intent(this, MainActivity.class);
//                                           PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
//                                                   PendingIntent.FLAG_UPDATE_CURRENT);
//                                           builder.setContentIntent(contentIntent);
//
//                                           // Add as notification
//                                           NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                                           manager.notify(0, builder.build());
                                           Toast.makeText(getApplicationContext() , "Expense Exceed" , Toast.LENGTH_SHORT).show();
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
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            databaseReference.child("Trip").child(tripId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try{
                        if(dataSnapshot.exists()){
                            String user = dataSnapshot.child("Admin").getValue().toString();
                            String end = dataSnapshot.child("Status").getValue().toString();
                            if(!end.equals("going")){
                                addExpense.setVisibility(View.GONE);
                                addMember.setVisibility(View.GONE);
                                endTrip.setVisibility(View.GONE);
                                exitTrip.setVisibility(View.GONE);

                            }
                            if(!user.equals(uid)){
                                addExpense.setVisibility(View.GONE);
                                addMember.setVisibility(View.GONE);
                                endTrip.setVisibility(View.GONE);
                                exitTrip.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    catch (Exception e){}
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            exitTrip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Trips.this);
                    alertDialogBuilder.setMessage("Are you sure to Exit?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            databaseReference.child("Trip").child(tripId).child("Members").child(uid).setValue(null);
                            dialog.dismiss();
                            startActivity(new Intent(Trips.this , TripsList.class));
                            finish();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
            });
            addExpense.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Trips.this,AddTripExpense.class);
                    intent.putExtra("Trip",tripId);
                    startActivity(intent);
                    finish();
                }
            });
            addMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Trips.this,AddMember.class);
                    intent.putExtra("Trip",tripId);
                    startActivity(intent);
                    finish();
                }
            });
            endTrip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Trips.this);
                    alertDialogBuilder.setMessage("Are you sure to Close Shared Expense?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            databaseReference.child("Trip").child(tripId).child("Status").setValue("end");
                            dialog.dismiss();
                            finish();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();

                }
            });
            userAttrs = new ArrayList<UserAttr>();
            memberList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            databaseReference.child("Trip").child(tripId).child("Members").orderByChild("name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    userAttrs.clear();
                    //profiledata.clear();
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        UserAttr p = dataSnapshot1.getValue(UserAttr.class);
                        userAttrs.add(p);
                    }
                    databaseReference.child("Trip").child(tripId).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try{
                                if(dataSnapshot.exists()){
                                    String user = dataSnapshot.child("Admin").getValue().toString();
                                    if(!user.equals(uid)){
                                        memberList.setAdapter(new MemberAdapter(userAttrs , getApplicationContext() , Trips.this , tripId,"no"));
                                    }
                                    else
                                        memberList.setAdapter(new MemberAdapter(userAttrs , getApplicationContext() , Trips.this , tripId,"yes"));
                                }
                            }
                            catch (Exception e){}
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            paymentAttrs = new ArrayList<PaymentAttr>();
            paymentList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            databaseReference.child("Trip").child(tripId).child("Payment").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        paymentAttrs.clear();
                        //profiledata.clear();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            PaymentAttr p = dataSnapshot1.getValue(PaymentAttr.class);
                            paymentAttrs.add(p);
                        }
                        Collections.reverse(paymentAttrs);
                        paymentList.setAdapter(new PaymentListAdapter(paymentAttrs, getApplicationContext(), Trips.this));
                    }
                    else{
                        paymentList.setVisibility(View.GONE);
                        p.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            databaseReference.child("Trip").child(tripId).child("Expense").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        try {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                if (dataSnapshot1.child("category").getValue().toString().equals("Fuel"))
                                    fuelT = fuelT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                if (dataSnapshot1.child("category").getValue().toString().equals("Sports"))
                                    sportsT = sportsT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                if (dataSnapshot1.child("category").getValue().toString().equals("Entertainment"))
                                    entertainmentT = entertainmentT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                if (dataSnapshot1.child("category").getValue().toString().equals("Food"))
                                    foodT = foodT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            }
                        }
                        catch (Exception e){}

                        fuel.setText(String.valueOf(fuelT));
                        sports.setText(String.valueOf(sportsT));
                        entertainment.setText(String.valueOf(entertainmentT));
                        food.setText(String.valueOf(foodT));

                    }
                    else{
                        fuel.setText("0");
                        sports.setText("0");
                        entertainment.setText("0");
                        food.setText("0");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            expenseList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            expenseAttrs = new ArrayList<ExpenseAttr>();
            databaseReference.child("Trip").child(tripId).child("Expense").addValueEventListener(new ValueEventListener() {
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
//                                                    Toast.makeText(getApplicationContext(), "No expense Found", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }



//        databaseReference.child("Trip").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                try {
//                    if (dataSnapshot.exists()) {
//                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                            databaseReference.child("Trip").child(dataSnapshot1.getKey()).child("Members").child(uid).addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                    if (dataSnapshot.exists()) {
//                                        tripId = dataSnapshot1.getKey();
//
//                                    } else {
//                                        view2.setVisibility(View.VISIBLE);
//                                        progressDialog.dismiss();
//                                        Toast.makeText(getApplicationContext(), "Trip Not Found!", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                }
//                            });
//                        }
//                    } else {
//                        view2.setVisibility(View.VISIBLE);
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), "Trip Not Found!", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//                    progressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


    }

    @Override
    int getContentViewId() {
        return R.layout.activity_trips;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_trip;
    }
}