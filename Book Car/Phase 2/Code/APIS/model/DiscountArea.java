package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscountArea {
    private int adminId;
    private String[] areas;
    public DiscountArea(@JsonProperty("adminId") int adminId,
                        @JsonProperty("areas")  String[] areas){
        this.adminId=adminId;
        this.areas=areas;
    }
    public int getAdminId(){return adminId;}
    public String[]getAreas(){return areas;}
}
