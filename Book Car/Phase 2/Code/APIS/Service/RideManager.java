package com.example.Phase2.Service;

import com.example.Phase2.model.Ride;

import java.util.ArrayList;
import java.util.Map;


public  class RideManager {

    private static int rideNumber;

    private static ArrayList<Ride> systemRides = new ArrayList<>();

    public static ArrayList<Ride> getRides() {
        return systemRides;
    }

    public RideManager() {
        rideNumber = 0;
    }

    public static int addNewRide(Object newRide) {

        Ride ride = (Ride) newRide;

        ride.setRideId(++rideNumber);

        systemRides.add(ride);
        return rideNumber;

    }

    //to only tESt
    public static void print() {

        for (Ride systemRide : systemRides) {
            Map<Integer, String> map = systemRide.getRideoffers();

            System.out.println("The offers of that ride is : ");
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                System.out.println("driver id = " + entry.getKey()
                        + ", Price = " + entry.getValue());
            }
            System.out.println("The PAssenger Name: " + systemRide.getPassengersNameAtRide() + " "
                    + "Passenger Id : " + systemRide.getPassengersIdAtRide() + " "
                    + "Source : " + systemRide.getSource() + " "
                    + "RideId: " + systemRide.getRideId() + " "
                    + "Destination: " + systemRide.getDestination() + " ");
        }

    }

    public static Ride search(int rideId, int userId) throws Exception {
        for (Ride systemRide : systemRides) {
            for (int i = 0; i < systemRide.getPassengers().length; i++) {
                if (systemRide.getRideId() == rideId && systemRide.getPassengers()[i].getUserId() == userId) {
                    return systemRide;
                }
            }
        }
        throw new Exception("You are not in this Ride");
    }

    public static void updateRide(Ride newRide, int rideId) {
        for (int i = 0; i < systemRides.size(); i++) {
            Ride ride = systemRides.get(i);
            if (ride.getRideId() == rideId) {
                systemRides.set(i, newRide);
                break;
            }

        }
    }

    public static ArrayList<Ride> searchRide(ArrayList<String> driverArea) {
        ArrayList<Ride> commonArea = new ArrayList<>();
        for (int i = 0; i < systemRides.size(); i++) {
            Ride ride = systemRides.get(i);
            if (driverArea.contains(ride.getSource())&&"Waiting".equals(ride.getStatus())) {
                commonArea.add(ride);
            }
        }
        return commonArea;
    }

    public static Ride searchById(int rideId) throws Exception {

        for (int i = 0; i < systemRides.size(); i++) {
            Ride ride = systemRides.get(i);
            if (ride.getRideId() == rideId) {
                return ride;
            }
        }
        throw new Exception("No eXIst");
    }

}




/*
public class RideManager {

    private static int rideNumber;

    private static ArrayList<Ride> systemRides = new ArrayList<>();

    public static ArrayList<Ride>getRides()

    {
        return systemRides;
    }


    public RideManager() {
        //systemRides = new ArrayList<>();
        rideNumber = 0;
    }

    public static int addNewRide(Object newRide) {

        Ride ride = (Ride) newRide;

        ride.setRideId(++rideNumber);

        systemRides.add(ride);
        return rideNumber;

    }

    //to only tESt
    public static void print() {

        for (Ride systemRide : systemRides) {
            Map<Integer, String> map = systemRide.getRideoffers();

            System.out.println("The offers of that ride is : ");
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                System.out.println("driver id = " + entry.getKey()
                        + ", Price = " + entry.getValue());
            }
            System.out.println("The PAssenger Name: " + systemRide.getpassenger().getUserName() + " "
                    + "Passenger Id : " + systemRide.getpassenger().getUserId() + " "
                    + "Source : " + systemRide.getSource() + " "
                    + "RideId: " + systemRide.getRideId() + " "
                    + "Destination: " + systemRide.getDestination() + " ");
        }

    }

    public static Ride search(int rideId, int accountId) throws Exception {
        for (Ride systemRide : systemRides) {

            if (systemRide.getRideId() == rideId && systemRide.getpassenger().getUserId() == accountId) {
                return systemRide;
            }
        }
        throw new Exception("You are not in this Ride");
    }

    public static void updateRide(Ride newRide, int rideId) {
        for (int i = 0; i < systemRides.size(); i++) {
            Ride ride = systemRides.get(i);
            if (ride.getRideId() == rideId) {
                systemRides.set(i, newRide);
                break;
            }

        }
    }

    public static ArrayList<Ride> searchRide(ArrayList<String> driverArea) {
        ArrayList<Ride> commonArea = new ArrayList<>();
        for (int i = 0; i < systemRides.size(); i++) {
            Ride ride = systemRides.get(i);
            if (driverArea.contains(ride.getSource())) {
                commonArea.add(ride);
            }
        }
        return commonArea;
    }

    public static Ride searchById(int rideId) throws Exception {

        for (int i = 0; i < systemRides.size(); i++) {
            Ride ride = systemRides.get(i);
            if (ride.getRideId() == rideId) {
                return ride;
            }
        }
        throw new Exception("No eXIst");
    }

}*/