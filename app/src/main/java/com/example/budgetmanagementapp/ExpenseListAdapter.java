package com.example.budgetmanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {
    ArrayList<ExpenseAttr> expenseAttrs;
    private Context context;

    public ExpenseListAdapter(ArrayList<ExpenseAttr> expenseAttrs, Context context) {
        this.context = context;
        this.expenseAttrs = expenseAttrs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.detail.setText(expenseAttrs.get(position).getDetail());
        holder.amount.setText(expenseAttrs.get(position).getAmount());
        holder.date.setText(expenseAttrs.get(position).getDate());
        String category = expenseAttrs.get(position).getCategory();
        if (category.equals("Food")) {
            holder.img.setBackgroundResource(R.drawable.food);
        } else if (category.equals("Fuel")) {
            holder.img.setBackgroundResource(R.drawable.fuel);
        } else if (category.equals("Shopping")) {
            holder.img.setBackgroundResource(R.drawable.shopping);
        }if(category.equals("Kids")) {
            holder.img.setBackgroundResource(R.drawable.kids);
        }if(category.equals("Clothes")) {
            holder.img.setBackgroundResource(R.drawable.cloths);
        }if(category.equals("Gift")) {
            holder.img.setBackgroundResource(R.drawable.gift);
        }if(category.equals("Sports")) {
            holder.img.setBackgroundResource(R.drawable.sports);
        }if(category.equals("Entertainment")) {
            holder.img.setBackgroundResource(R.drawable.entertainment);
        }if(category.equals("Others")) {
            holder.img.setBackgroundResource(R.drawable.logo);
        }


    }

    @Override
    public int getItemCount() {
        return expenseAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView detail, date, amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            detail = (TextView) itemView.findViewById(R.id.txtDetail);
            date = (TextView) itemView.findViewById(R.id.txtDate);
            amount = (TextView) itemView.findViewById(R.id.txtAmount);

        }
    }
}
