package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RideId {
    private int rideId;
    public RideId(@JsonProperty ("rideId") int rideId){
        this.rideId=rideId;
    }
    public int getRideId(){return rideId;}
}
