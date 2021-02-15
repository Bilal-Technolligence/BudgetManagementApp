package com.example.budgetmanagementapp;

public class TripAttr {
    String Id;
    String Budget;
    String Title;
    String Admin;


    public TripAttr() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String budget) {
        Budget = budget;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAdmin() {
        return Admin;
    }

    public void setAdmin(String admin) {
        Admin = admin;
    }

    public TripAttr(String id, String budget, String title, String admin) {
        Id = id;
        Budget = budget;
        Title = title;
        Admin = admin;
    }
}
