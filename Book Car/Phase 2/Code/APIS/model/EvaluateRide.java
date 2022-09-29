package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EvaluateRide {


    private int passengerId;
    private int RideId;
    private int starNum;


    public EvaluateRide
            (@JsonProperty("passengerId") int passengerId,
             @JsonProperty("RideId") int RideId,
             @JsonProperty("starNum")int starNum ) {
        this.passengerId = passengerId;
        this.RideId = RideId;
        this.starNum=starNum;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public int getRideId() {
        return RideId;
    }

    public int getStar() {
        return starNum;
    }
}
