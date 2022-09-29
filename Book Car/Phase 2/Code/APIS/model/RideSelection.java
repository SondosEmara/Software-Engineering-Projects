package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RideSelection {

    private int passengerId;
    private int driverId;
    RideSelection(@JsonProperty("passengerId") int passengerId,@JsonProperty("driverId") int driverId){
        this.passengerId=passengerId;
        this.driverId=driverId;
    }
    public int getPassengerId(){return passengerId;}
    public int getDriverId(){return driverId;}
}
