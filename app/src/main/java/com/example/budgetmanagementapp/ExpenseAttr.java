package com.example.budgetmanagementapp;

public class ExpenseAttr {
    String amount;
    String category;
    String detail;
    String id;
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExpenseAttr(String amount, String category, String detail, String id, String date) {
        this.amount = amount;
        this.category = category;
        this.detail = detail;
        this.id = id;
        this.date = date;
    }

    public ExpenseAttr() {
    }
}
