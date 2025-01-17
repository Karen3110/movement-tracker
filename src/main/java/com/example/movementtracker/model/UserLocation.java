package com.example.movementtracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserLocation {
    private int userId;
    private double longitude;
    private double latitude;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy, h:mm:ss a")
    private Date date;

    public UserLocation(int userId, double longitude, double latitude, Date date) {
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
