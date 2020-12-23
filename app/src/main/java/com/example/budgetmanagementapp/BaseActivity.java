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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.spending) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (itemId == R.id.transaction) {
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (itemId == R.id.history) {
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if (itemId == R.id.trip) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        else if (itemId == R.id.logout) {
            Intent intent = new Intent(this , LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }


    abstract int getContentViewId();


}
