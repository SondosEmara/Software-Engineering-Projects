package com.example.Phase2.model;

import com.example.Phase2.Service.RideManager;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Driver extends User {

    private String drivingLicense;
    private String nationalId;
    private Admin admin;
    private double averageRate=0;
    private  ArrayList<String> favoriteArea=new ArrayList<>();
    private int currentRideId=-1;
    private wallet driverWallet=new wallet();


    public Driver(){}
    public Driver(@JsonProperty("userName") String userName,
                  @JsonProperty("email") String  email,
                  @JsonProperty("mobileNum") String  mobileNum,
                  @JsonProperty("password") String password,
                  @JsonProperty("drivingLicense") String drivingLicense,
                  @JsonProperty("nationalId") String  nationalId){


        this.setUserName(userName);
        this.setEmail(email);
        this.setMobileNum(mobileNum);
        this.setPassword(password);
        this.nationalId=nationalId;
        this.drivingLicense=drivingLicense;


    }
    public wallet getWallet(){
        return driverWallet;
    }
    public void setWallet(wallet walletdriver){
        this.driverWallet=walletdriver;
    }

    public void setAdmin(Admin admin){this.admin=admin;}

    public void setcurrentRideId(int currentRideId){
        this.currentRideId=currentRideId;
    }
    public int getCurrentRideId() {
        return currentRideId;
    }
    public String getNationalId() {
        return nationalId;
    }

    public String getDriverLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Admin getAdmin() {
        return admin;
    }

    public double getAverageRate() {

        ArrayList<Ride>rides= RideManager.getRides();

        int counter=0;double rate=0;
        for (Ride ride : rides) {

            if(ride.getDriver()!=null&&ride.getDriver().getUserId()==this.getUserId()){
                rate+=ride.getStar();
                counter++;
            }
        }
        if(counter==0) return 0;
        averageRate=rate/counter;
        return averageRate;
    }
    public void addFavouriteArea(String area){
        favoriteArea.add(area);
    }

    public ArrayList<String> getfavouruteArea(){return favoriteArea;}

}



/*
import com.example.Phase2.Service.RideManager;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Driver extends User {

    private String drivingLicense;
    private String nationalId;
    private Admin admin;
    private double averageRate=0;
    private  ArrayList<String> favoriteArea=new ArrayList<>();
    private int currentRideId=-1;
    public void setAdmin(Admin admin){this.admin=admin;}

    public Driver(@JsonProperty("userName") String userName,
                  @JsonProperty("email") String  email,
                  @JsonProperty("mobileNum") String  mobileNum,
                  @JsonProperty("password") String password,
                  @JsonProperty("drivingLicense") String drivingLicense,
                  @JsonProperty("nationalId") String  nationalId){


        this.setUserName(userName);
        this.setEmail(email);
        this.setMobileNum(mobileNum);
        this.setPassword(password);
        this.nationalId=nationalId;
        this.drivingLicense=drivingLicense;


    }
    public Driver(){}
    public void setcurrentRideId(int currentRideId){
        this.currentRideId=currentRideId;
    }
    public int getCurrentRideId() {
        return currentRideId;
    }
    public String getNationalId() {
        return nationalId;
    }

    public String getDriverLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Admin getAdmin() {
        return admin;
    }

    public double getAverageRate() {

        ArrayList<Ride> rides= RideManager.getRides();

        int counter=0;double rate=0;
        for (Ride ride : rides) {

            if(ride.getDriver()!=null&&ride.getDriver().getUserId()==this.getUserId()){
                rate+=ride.getStar();
                counter++;
            }
        }
        if(counter==0) return 0;
        averageRate=rate/counter;
        System.out.println("AVERAGERATE:"+averageRate);
        return averageRate;
    }
    public void addFavouriteArea(String area){
        favoriteArea.add(area);
    }

    public ArrayList<String>getfavouruteArea(){return favoriteArea;}

}
*/