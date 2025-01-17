package com.example.movementtracker.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class DistanceResponse {
    private int userId;
    private Date dateStart;
    private Date dateEnd;
    private double distanceInKm;

    public DistanceResponse(int userId, Date dateStart, Date dateEnd, double distanceInKm) {
        this.userId = userId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.distanceInKm = distanceInKm;
    }

    public double getDistanceInMiles() {
        return distanceInKm * 0.621371;
    }
}
