package com.example.budgetmanagementapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PaymentListAdapter extends RecyclerView.Adapter<PaymentListAdapter.ViewHolder> {
    ArrayList<PaymentAttr> paymentAttrs;
    private Context context;
    Activity activity;

    public PaymentListAdapter(ArrayList<PaymentAttr> paymentAttrs, Context context , Activity activity) {
        this.context = context;
        this.paymentAttrs = paymentAttrs;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(paymentAttrs.get(position).getName());
        holder.amount.setText(paymentAttrs.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return paymentAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  name ,amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtName);
            amount = (TextView) itemView.findViewById(R.id.txtEnded);
        }
    }
}
