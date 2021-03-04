package com.example.budgetmanagementapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends BaseActivity {
    TextView food, spent, remaining, fuel, shopping, kids, clothes, gifts, sports, entertainment, other, progress, incomeTotal;
    ProgressBar progressBar;
    Button addExpense, addIncome, btnGenerateNotification;
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    int total = 0, fuelT = 0, shoppingT = 0, kidsT = 0, clothesT = 0, giftT = 0;
    int income = 0, sportsT = 0, entertainmentT = 0, othersT = 0, foodT = 0;
    ProgressDialog progressDialog;
    /////Only for Notification////
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    /////////
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //  ((AppCompatActivity)this).getSupportActionBar().setTitle("Main");
        //  ((AppCompatActivity)this).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..... ");
        progressDialog.show();
        spent = (TextView) findViewById(R.id.txtIncomeSpent);
        remaining = (TextView) findViewById(R.id.txtRemainingSpent);
        fuel = (TextView) findViewById(R.id.txtFuel);
        shopping = (TextView) findViewById(R.id.txtShopping);
        kids = (TextView) findViewById(R.id.txtKids);
        clothes = (TextView) findViewById(R.id.txtClothes);
        gifts = (TextView) findViewById(R.id.txtGift);
        sports = (TextView) findViewById(R.id.txtSports);
        entertainment = (TextView) findViewById(R.id.txtEntertainment);
        other = (TextView) findViewById(R.id.txtOthers);
        food = (TextView) findViewById(R.id.txtFood);
        incomeTotal = (TextView) findViewById(R.id.txtIncome);
        progress = (TextView) findViewById(R.id.text_view_progress);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        pieChart = findViewById(R.id.pieChart);
        pieChart .setVisibility(View.GONE);

        addExpense = (Button) findViewById(R.id.btnExpense);
        // btnGenerateNotification = (Button) findViewById(R.id.btnSendNotifications);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (0 <= 1) {
                    try {
                        Thread.sleep(5000);
                        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                        Toast.makeText(MainActivity.this, ""+uid, Toast.LENGTH_LONG).show();
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                        databaseReference.child("ExpenseNoti").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                        try {
                                            String status = dataSnapshot1.child("status").getValue().toString();
                                            String senderId = dataSnapshot1.child("senderid").getValue().toString();
                                            if (status.equals("unread") && uid.equals(senderId)) {
                                                String id = dataSnapshot1.child("id").getValue().toString();
                                                // String name = dataSnapshot1.child("name").getValue().toString();
                                                String msg = dataSnapshot1.child("description").getValue().toString();
                                                databaseReference.child("ExpenseNoti").child(id).child("status").setValue("read");
                                                scheduleNotification(getNotification(msg), 5000);

                                            }
                                        } catch (Exception e) {
                                        }
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        // return START_STICKY;
////////////////////////////////////////////
        addIncome = (Button) findViewById(R.id.btnIncome);
        final Calendar cldr = Calendar.getInstance();
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddExpense.class));
                finish();
            }
        });
        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddIncome.class));
                finish();
            }
        });


        //////////////////
        databaseReference.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    income = Integer.parseInt(dataSnapshot.child("income").getValue().toString());
                    incomeTotal.setText(String.valueOf(income));
                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month + 1)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                try {
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                        total = total + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                    }
                                    spent.setText(String.valueOf(total));
                                    Integer remain = income - total;
                                    if (total >= income) {
                                        final String push = FirebaseDatabase.getInstance().getReference().child("Notification").push().getKey();

                                        databaseReference.child("ExpenseNoti").child(uid).child("description").setValue("Budget Exeed from income");
                                        databaseReference.child("ExpenseNoti").child(uid).child("status").setValue("unread");
                                        databaseReference.child("ExpenseNoti").child(uid).child("title").setValue("Budget Alert");
                                        databaseReference.child("ExpenseNoti").child(uid).child("senderid").setValue(uid);
                                        databaseReference.child("ExpenseNoti").child(uid).child("id").setValue(uid);
//
                                    }
                                    remaining.setText(String.valueOf(remain));
                                    int result = (int) Math.round((total * 100) / income);
                                    String p = String.valueOf(result);
                                    progressBar.setProgress(result);
                                    progress.setText(p + "%");
                                } catch (Exception e) {
                                }
                            } else {
                                progressBar.setProgress(0);
                                progress.setText("0 %");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {
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
                            if (dataSnapshot1.child("category").getValue().toString().equals("Shopping"))
                                shoppingT = shoppingT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Fuel"))
                                fuelT = fuelT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Kids"))
                                kidsT = kidsT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Clothes"))
                                clothesT = clothesT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Gift"))
                                giftT = giftT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Sports"))
                                sportsT = sportsT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Entertainment"))
                                entertainmentT = entertainmentT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Others"))
                                othersT = othersT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Food"))
                                foodT = foodT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }

                    } catch (Exception e) {
                    }

                    shopping.setText(String.valueOf(shoppingT));
                    fuel.setText(String.valueOf(fuelT));
                    kids.setText(String.valueOf(kidsT));
                    clothes.setText(String.valueOf(clothesT));
                    gifts.setText(String.valueOf(giftT));
                    sports.setText(String.valueOf(sportsT));
                    entertainment.setText(String.valueOf(entertainmentT));
                    other.setText(String.valueOf(othersT));
                    food.setText(String.valueOf(foodT));
                    progressDialog.dismiss();


                } else {
                    pieChart.setVisibility(View.GONE);
                    shopping.setText("0");
                    fuel.setText("0");
                    kids.setText("0");
                    clothes.setText("0");
                    gifts.setText("0");
                    sports.setText("0");
                    entertainment.setText("0");
                    other.setText("0");
                    food.setText("0");
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
                            if (dataSnapshot1.child("category").getValue().toString().equals("Shopping"))
                                shoppingT = shoppingT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Fuel"))
                                fuelT = fuelT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Kids"))
                                kidsT = kidsT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Clothes"))
                                clothesT = clothesT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Gift"))
                                giftT = giftT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Sports"))
                                sportsT = sportsT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Entertainment"))
                                entertainmentT = entertainmentT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Others"))
                                othersT = othersT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                            if (dataSnapshot1.child("category").getValue().toString().equals("Food"))
                                foodT = foodT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        }
                        pieChart.setUsePercentValues(true);
                        List<PieEntry> pi = new ArrayList<>();
                       if(!String.valueOf(shoppingT).equals("0"))
                        pi.add(new PieEntry(Integer.parseInt(String.valueOf(shoppingT)), "Shopping"));
                        if(!String.valueOf(fuelT).equals("0"))
                        pi.add(new PieEntry(Integer.parseInt(String.valueOf(fuelT)), "Fuel"));
                        if(!String.valueOf(kidsT).equals("0"))
                        pi.add(new PieEntry(Integer.parseInt(String.valueOf(kidsT)), "Kids"));
                        if(!String.valueOf(clothesT).equals("0"))
                            pi.add(new PieEntry(Integer.parseInt(String.valueOf(clothesT)), "Clothes"));
                        if(!String.valueOf(giftT).equals("0"))
                            pi.add(new PieEntry(Integer.parseInt(String.valueOf(giftT)), "Gift"));
                        if(!String.valueOf(sportsT).equals("0"))
                            pi.add(new PieEntry(Integer.parseInt(String.valueOf(sportsT)), "Sports"));
                        if(!String.valueOf(entertainmentT).equals("0"))
                            pi.add(new PieEntry(Integer.parseInt(String.valueOf(entertainmentT)), "Entertainment"));
                        if(!String.valueOf(othersT).equals("0"))
                            pi.add(new PieEntry(Integer.parseInt(String.valueOf(othersT)), "Other"));
                        if(!String.valueOf(foodT).equals("0"))
                            pi.add(new PieEntry(Integer.parseInt(String.valueOf(foodT)), "Food"));

                        PieDataSet pieDataSet = new PieDataSet(pi, "");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        pieDataSet.setValueTextColor(Color.BLACK);
                        pieDataSet.setValueTextSize(10f);

                        PieData pieData = new PieData(pieDataSet);

                        pieChart.setData(pieData);
                        pieChart.getDescription().setEnabled(false);
                        pieChart.setCenterText("Total");
                        pieChart.animate();
                        pieChart.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                    }


                }
                else{
                    pieChart.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void scheduleNotification(Notification notification, int delay) {
        Intent notificationIntent = new Intent(this, NotificationGernetor.class);
        // Intent notificationIintent = new Intent(this, NotificationActivity.class);
        notificationIntent.putExtra(NotificationGernetor.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationGernetor.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_channel_id);
        builder.setContentTitle("Budget Notification");
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_spending;
    }

}