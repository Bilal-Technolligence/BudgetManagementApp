package com.example.budgetmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentActivity extends BaseActivity {
    String tripId;
    CardView pay;
    EditText amount;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference();
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //etContentView(R.layout.activity_payment);
        tripId = getIntent().getStringExtra("Trip");
        pay = (CardView) findViewById(R.id.pay);
        amount = (EditText) findViewById(R.id.txtAmount);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Amount = amount.getText().toString();
                if (Amount.equals("")) {
                    amount.setError("Enter Valid Amount");
                    amount.setFocusable(true);
                } else {
                    reference.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {
                                if (dataSnapshot.exists()) {
                                    String name = dataSnapshot.child("name").getValue().toString();
                                    final String push = FirebaseDatabase.getInstance().getReference().child("Payment").push().getKey();
                                    reference.child("Trip").child(tripId).child("Payment").child(push).child("id").setValue(uid);
                                    reference.child("Trip").child(tripId).child("Payment").child(push).child("name").setValue(name);
                                    reference.child("Trip").child(tripId).child("Payment").child(push).child("amount").setValue(Amount);
                                    Toast.makeText(getApplicationContext(), "Payment Saved", Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(PaymentActivity.this,Trips.class);
                                    intent.putExtra("id",tripId);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            catch (Exception e){
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }
        });

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_payment;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_trip;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(PaymentActivity.this,Trips.class);
        intent.putExtra("id",tripId);
        startActivity(intent);
        finish();
    }
}