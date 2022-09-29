package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Desposit {
    private int passengerId;
    private int despositValue;
    public Desposit(@JsonProperty("passengerId") int passengerId,
                    @JsonProperty("despositValue") int  despositValue){
        this.despositValue=despositValue;
        this.passengerId=passengerId;
    }
    public int getPassengerId(){return passengerId;}
    public int getDespositValue(){return  despositValue;}

}
