package com.example.budgetmanagementapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class MainActivity extends BaseActivity {
    TextView food, spent, remaining, fuel, shopping, kids, clothes, gifts, sports, entertainment, other, progress, incomeTotal;
    ProgressBar progressBar;
    Button addExpense, addIncome;
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    int total = 0 , fuelT = 0 , shoppingT= 0 , kidsT = 0, clothesT= 0, giftT = 0;
    int income = 0 , sportsT = 0,entertainmentT= 0, othersT= 0, foodT=0;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
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

        addExpense = (Button) findViewById(R.id.btnExpense);
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
        databaseReference.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    income = Integer.parseInt(dataSnapshot.child("income").getValue().toString());
                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month + 1)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    total = total + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                                }
                                spent.setText(String.valueOf(total));
                                Integer remain = income - total;
                                remaining.setText(String.valueOf(remain));
                                incomeTotal.setText(String.valueOf(income));
                                int result = (int) Math.round ((total * 100) / income);
                                String p =String.valueOf(result);
                                progressBar.setProgress(result);
                                progress.setText(p + "%");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

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
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        if(dataSnapshot1.child("category").getValue().toString().equals("Shopping"))
                        shoppingT = shoppingT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        if(dataSnapshot1.child("category").getValue().toString().equals("Fuel"))
                            fuelT = fuelT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        if(dataSnapshot1.child("category").getValue().toString().equals("Kids"))
                            kidsT = kidsT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        if(dataSnapshot1.child("category").getValue().toString().equals("Clothes"))
                            clothesT = clothesT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        if(dataSnapshot1.child("category").getValue().toString().equals("Gift"))
                            giftT = giftT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        if(dataSnapshot1.child("category").getValue().toString().equals("Sports"))
                            sportsT = sportsT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        if(dataSnapshot1.child("category").getValue().toString().equals("Entertainment"))
                            entertainmentT = entertainmentT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        if(dataSnapshot1.child("category").getValue().toString().equals("Others"))
                            othersT = othersT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
                        if(dataSnapshot1.child("category").getValue().toString().equals("Food"))
                            foodT = foodT + Integer.parseInt(dataSnapshot1.child("amount").getValue().toString());
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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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