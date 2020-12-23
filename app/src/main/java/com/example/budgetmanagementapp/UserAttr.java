package com.example.budgetmanagementapp;

public class UserAttr {
    String Id;
    String Name;
    String Email;
    String Contact;
    String ImageUrl;
    String Income;


    public UserAttr() {
    }

    public UserAttr(String id, String name, String email, String contact, String imageUrl, String income) {
        Id = id;
        Name = name;
        Email = email;
        Contact = contact;
        ImageUrl = imageUrl;
        Income = income;
    }

    public String getIncome() {
        return Income;
    }

    public void setIncome(String income) {
        Income = income;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
