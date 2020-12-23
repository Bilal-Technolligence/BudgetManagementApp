package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddIncome extends BaseActivity {
    CardView Update;
    EditText income;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_income);
        Update = (CardView) findViewById(R.id.update);
        income = (EditText) findViewById(R.id.txtIncome);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        reference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    income.setText(dataSnapshot.child("income").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Income = income.getText().toString();
                if (Income.equals("")) {
                    income.setError("Enter Valid Income");
                    income.setFocusable(true);
                }
                else
                reference.child(uid).child("income").setValue(Income);
                Toast.makeText(getApplicationContext(),"Income Updated", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddIncome.this,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_add_income;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_history;
    }


}