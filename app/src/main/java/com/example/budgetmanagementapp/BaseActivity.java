package com.example.budgetmanagementapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener{
    protected BottomNavigationView navigationView;
    protected DrawerLayout drawerLayout;
    protected NavigationView drawerNavigationView;
    ImageView imageView;
    String uid;
    TextView textView,pointsTextView;
    // private CallbackManager callbackManager;
    protected ActionBarDrawerToggle drawerToggle;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

///Buttom Navigation
        navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(this);
        //drawer navigation
        drawerLayout = (DrawerLayout) findViewById(R.id.drawarLayout);
        //adding drawar button
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  ((AppCompatActivity)this).getSupportActionBar().setTitle("Add Expense");
      //  ((AppCompatActivity)this).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //drawer navbar item click
        drawerNavigationView = (NavigationView) findViewById(R.id.drawerNavigationView);
        drawerNavigationView.setNavigationItemSelectedListener(this);

        //header click navbar
        View headerview = drawerNavigationView.getHeaderView(0);
        imageView = (ImageView) headerview.findViewById(R.id.profile_image);
        textView=(TextView) headerview.findViewById(R.id.headeruname);

         uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.child( "Users" ).child( uid ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String eName = dataSnapshot.child( "name" ).getValue().toString();
                    textView.setText(String.valueOf( eName ));
                    if(dataSnapshot.child( "imageUrl" ).getValue().toString().equals( " " )) {
//                        Picasso.get().load( dataSnapshot.child( "imageurl" ).getValue().toString() ).into( imageView );
                    }
                    else {
                        Picasso.get().load( dataSnapshot.child( "imageUrl" ).getValue().toString() ).into( imageView );

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );



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
        }
//        else if (itemId == R.id.nav_history) {
//            Intent intent=new Intent(this, History.class);
//            startActivity(intent);
//            finish();
//        }

        else if (itemId == R.id.nav_trip) {
            startActivity(new Intent(this, TripsList.class));
            finish();
        }
        else if (itemId == R.id.nav_notifications) {
            Intent intent = new Intent(this , NotificationActivity.class);
            startActivity(intent);
            finish();
        }
        else if (itemId == R.id.nav_prediction) {
           // startActivity(new Intent(this, TripsList.class));
            Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();
           // finish();
        }

        else if (itemId == R.id.nav_setting) {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        }
        else if (itemId == R.id.nav_logout) {
//            //shared prefrences
//            SharedPreferences prefs = getSharedPreferences("Log", MODE_PRIVATE);
//            boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);
//            if (isLoggedIn) {
            //    String userId=prefs.getString("id","");
                if(!uid.equals("")) {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setMessage("Are you sure want to logout?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Snackbar.make(drawerLayout, "Logout ok", Snackbar.LENGTH_LONG).show();
                            Save.save(getApplicationContext(),"session","false");
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            recreate();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
            }
            else{
                Snackbar.make(drawerLayout, "You are not login", Snackbar.LENGTH_LONG).show();
            }




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
