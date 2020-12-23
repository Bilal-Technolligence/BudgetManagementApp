package com.example.budgetmanagementapp;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

public class MainActivity extends BaseActivity {
TextView spent, remaining, fuel, shopping, kids, clothes, gifts, sports, entertainment, other, progress;
ProgressBar progressBar;
Button addExpense , addIncome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        spent = (TextView) findViewById(R.id.txtIncomeSpent);
        remaining= (TextView) findViewById(R.id.txtRemainingSpent);
        fuel= (TextView) findViewById(R.id.txtFuel);
        shopping= (TextView) findViewById(R.id.txtShopping);
        kids = (TextView) findViewById(R.id.txtKids);
        clothes= (TextView) findViewById(R.id.txtClothes);
        gifts= (TextView) findViewById(R.id.txtGift);
        sports= (TextView) findViewById(R.id.txtSports);
        entertainment= (TextView) findViewById(R.id.txtEntertainment);
        other= (TextView) findViewById(R.id.txtOthers);
        progress= (TextView) findViewById(R.id.text_view_progress);
        progressBar= (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setProgress(50);
        progress.setText("50");
        addExpense= (Button) findViewById(R.id.btnExpense);
        addIncome= (Button) findViewById(R.id.btnIncome);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddExpense.class));
                finish();
            }
        });
        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddIncome.class));
                finish();
            }
        });


    }

    @Override
    int getContentViewId() {
        return  R.layout.activity_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_spending;
    }

}