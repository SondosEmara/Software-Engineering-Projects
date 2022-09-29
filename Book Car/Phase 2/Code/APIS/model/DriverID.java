package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DriverID {

    private int driverId;
    DriverID(@JsonProperty("driverId") int driverId){
        this.driverId=driverId;
    }
    public int getDriverId(){return driverId;}
}
