package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DriverSelectRide {
    private int driverId;
    private int rideId;
    private String price;

    DriverSelectRide(@JsonProperty("driverId") int driverId,@JsonProperty("rideId") int rideId,
                     @JsonProperty("price") String price){
        this.driverId=driverId;
        this.rideId=rideId;
        this.price=price;
    }
    public int getDriverId(){return driverId;}
    public int getRideId(){return rideId;}
    public String getPrice(){return price;}
}
