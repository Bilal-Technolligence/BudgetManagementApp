package com.example.budgetmanagementapp;

public class PaymentAttr {
    String Id;
    String Amount;
    String Name;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public PaymentAttr(String id, String amount, String name) {
        Id = id;
        Amount = amount;
        Name = name;
    }

    public PaymentAttr() {
    }
}
