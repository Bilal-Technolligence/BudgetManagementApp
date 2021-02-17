package com.example.budgetmanagementapp;

public class notificationAttr {
    String senderid;
    String receiverid;
    String description;
    String title;
    String date;
    String time;
    String status;

    public notificationAttr() {
    }

    public notificationAttr(String senderid, String receiverid, String description, String title, String date, String time, String status) {
        this.senderid = senderid;
        this.receiverid = receiverid;
        this.description = description;
        this.title = title;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
