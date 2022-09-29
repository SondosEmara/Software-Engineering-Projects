package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestRide {

    private int passengerId;
    private String source;
    private String destination;
    private int numberOfPassengers;
    private int[] passengers;
    RequestRide(@JsonProperty("passengerId") int passengerId,
                @JsonProperty("numberOfPassengers")int numberOfPassengers,
                @JsonProperty("passengers")int[] passengers,
                @JsonProperty("source") String source,
                @JsonProperty("destination") String destination){

        this.destination=destination;
        this.passengerId=passengerId;
        this.source=source;
        this.numberOfPassengers=numberOfPassengers;
        this.passengers=passengers;

    }

    public int getnumberOfPassengers(){return numberOfPassengers;}
    public int getPassengerId(){

        return passengerId;
    }
    public String getSource(){return source;}

    public String getDestination(){return destination;}

    public int[]getpassengers(){return passengers;}
}
