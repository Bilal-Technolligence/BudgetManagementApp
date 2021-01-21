package com.example.budgetmanagementapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    protected BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());


        navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(this);


    }
    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_spending) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (itemId == R.id.nav_transaction) {
            Intent intent=new Intent(this, TransactionActivity.class);
            startActivity(intent);
            finish();
        } else if (itemId == R.id.nav_history) {
            Intent intent=new Intent(this, History.class);
            startActivity(intent);
            finish();
        }
        else if (itemId == R.id.nav_setting) {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        }
        else if (itemId == R.id.nav_trip) {
            startActivity(new Intent(this, Trips.class));
            finish();
        }
//        else if (itemId == R.id.nav_logout) {
//            Intent intent = new Intent(this , LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
        return true;
    }

    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }
    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }
//    void selectBottomNavigationBarItem(int itemId) {
//        Menu menu = navigationView.getMenu();
//        for (int i = 0, size = menu.size(); i < size; i++) {
//            MenuItem item = menu.getItem(i);
//            boolean shouldBeChecked = item.getItemId() == itemId;
//            if (shouldBeChecked) {
//                item.setChecked(true);
//                break;
//            }
//        }
//    }
    abstract int getContentViewId();

    abstract int getNavigationMenuItemId();

}
