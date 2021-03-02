package com.example.budgetmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddExpense extends BaseActivity {
    Spinner category;
    String cat = "";
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    EditText amount, detail;
    Button add;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_expense);

        amount = (EditText) findViewById(R.id.amount);
        detail = (EditText) findViewById(R.id.detail);
        category = findViewById(R.id.spinnerCategory);
        add = findViewById(R.id.btnAdd);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Adding..... ");
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cat = "Fuel";
            }
        });
        final Calendar cldr = Calendar.getInstance();
        int date = cldr.get(Calendar.DATE);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        String day = String.valueOf(date) +"-"+String.valueOf(month+1)+"-"+String.valueOf(year) ;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Ammount = amount.getText().toString().toUpperCase();
                String Detail = detail.getText().toString();
                if (Ammount.equals("")) {
                    amount.setError("Enter Valid Amount");
                    amount.setFocusable(true);
                } else if (Detail.equals("")) {
                    detail.setError("Enter Valid Detail");
                    detail.setFocusable(true);
                } else {
                    progressDialog.show();
                    final String push = FirebaseDatabase.getInstance().getReference().child("Expense").push().getKey();
                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).child(push).child("id").setValue(push);
                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).child(push).child("category").setValue(cat);
                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).child(push).child("detail").setValue(Detail);
                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).child(push).child("amount").setValue(Ammount);
                    databaseReference.child("Expense").child(uid).child(String.valueOf(year)).child(String.valueOf(month+1)).child(push).child("date").setValue(day);
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Expense created.", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(AddExpense.this ,MainActivity.class ));
                    finish();

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddExpense.this ,MainActivity.class ));
        finish();
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_add_expense;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_spending;
    }
}