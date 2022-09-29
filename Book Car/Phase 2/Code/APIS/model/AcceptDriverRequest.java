package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcceptDriverRequest {

    private int adminId;
    private int driverId;
    AcceptDriverRequest(@JsonProperty("adminId") int adminId,
                        @JsonProperty("driverId") int driverId){

        this.adminId=adminId;
        this.driverId=driverId;
    }

    public int getAdminId(){return adminId;}
    public int getDriverId(){return driverId;}

}
