package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddNewArea {
    private int driverId;
    private String[]area;
    AddNewArea(@JsonProperty("driverId") int driverId,
                        @JsonProperty("areas") String[] area ){
        this.driverId=driverId;
        this.area=area;
    }

    public String[]getArea(){return area;}
    public int getDriverId(){return driverId;}
}
