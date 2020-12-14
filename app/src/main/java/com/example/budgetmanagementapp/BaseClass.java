package com.example.budgetmanagementapp;

import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

public abstract class BaseClass extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
    protected NavigationView drawerNavigationView;
    ImageView imageView;
    protected ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
