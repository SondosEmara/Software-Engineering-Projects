package com.example.Phase2.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ride {

        private String source ;
        private String destination ;
        private int rideId;
        private Passenger[]passengers;
        private String rideStatus;
        //int is driverId and the String is Price.
        private Map<Integer, String> rideOffers = new HashMap();
        private Driver driver=new Driver();
        private int starNum=0;
        private ArrayList<String> notifications = new ArrayList<String>();
        private LocalDateTime arrivedTimeToUser;
        private LocalDateTime arrivedTimeToDestination;
        private int numberOfPassengers;
        private double discount=0;


        public double getDiscount(){return discount;}
        public void setDiscount(double discount ){
                this.discount=discount;
        }
        public void setStar(int starNum){this.starNum=starNum;}
        public int getStar(){return starNum;}
        public String getSource() {
                return this.source;
        }

        public void setSource(String source) {
                this.source = source;
        }

        public void setDestination(String destination){this.destination=destination;}

        public String getDestination(){return destination;}

        public int getRideId() {
                return this.rideId;
        }

        public void setRideId(int rideId) {
                this.rideId = rideId;
        }

        public void setStatus(String rideStatus) {
                this.rideStatus=rideStatus;
        }
        public String getStatus(){return rideStatus;}


        public void addToMap(int driverId,String price){
                rideOffers.put(driverId, price);

        }

        public  Map<Integer, String>getRideoffers(){return rideOffers;}

        public void setDriver(Driver driver) {
                this.driver=driver;
        }

        public Driver getDriver(){return driver;}

        public LocalDateTime getArrivedTimeToUser() {
                return arrivedTimeToUser;
        }

        public void setArrivedTimeToUser(LocalDateTime arrivedTimeToUser) {
                this.arrivedTimeToUser = arrivedTimeToUser;
        }

        public LocalDateTime getArrivedTimeToDestination() {
                return arrivedTimeToDestination;
        }

        public void setArrivedTimeToDestination(LocalDateTime arrivedTimeToDestination) {
                this.arrivedTimeToDestination = arrivedTimeToDestination;
        }

        public void addRideNotification(String str){
                notifications.add(str);
        }

        public ArrayList<String> getNotifications(){
                return this.notifications;
        }

        public int getNumberOfPassengers() {
                return numberOfPassengers;
        }
        public void setNumberOfPassengers(int numberOfPassengers) {
                this.numberOfPassengers = numberOfPassengers;
                passengers=new Passenger[numberOfPassengers+1];
        }

        public void addPassengerToTheRide(Passenger p,int i){

                passengers[i]=p;
                //System.out.println("IN ADD FUNNNNNNNNNNNNNNNNNNNCTION"+passengers[i].getUserName());
        }
        public Passenger[]getPassengers(){
                return passengers;
        }

        public String getPassengersNameAtRide(){
                String temp = "";
                for (int i = 0 ; i < passengers.length ; i++){
                        temp += passengers[i].getUserName() + ", ";
                }
                return temp;
        }

        public String getPassengersMobileAtRide(){
                String temp = "";
                for (int i = 0 ; i < passengers.length ; i++){
                        temp += passengers[i].getMobileNum() + ", ";
                }
                return temp;
        }

        public String getPassengersIdAtRide(){
                String temp = "";
                for (int i = 0 ; i < passengers.length; i++){
                        temp += passengers[i].getUserId() + ", ";
                }
                return temp;
        }
}

/*
import java.util.HashMap;
import java.util.Map;

public class Ride {
        private String source ;
        private String destination ;
        private int rideId;
        private Passenger passenger;
        private String rideStatus;
        //int is driverId and the String is Price.
        private Map<Integer, String> rideOffers = new HashMap();
        private Driver driver;
        private int starNum=0;


        public void setStar(int starNum){this.starNum=starNum;}
        public int getStar(){return starNum;}
        public String getSource() {
            return this.source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setDestination(String destination){this.destination=destination;}

        public String getDestination(){return destination;}

        public int getRideId() {
            return this.rideId;
        }

        public void setRideId(int rideId) {
            this.rideId = rideId;
        }

        public void setPassenger(Passenger passenger) {
            this.passenger=passenger;

        }

        public Passenger getpassenger(){return passenger;}

        public void setStatus(String rideStatus) {
            this.rideStatus=rideStatus;
        }
        public String getStatus(){return rideStatus;}


        public void addToMap(int driverId,String price){
            rideOffers.put(driverId, price);

        }

        public  Map<Integer, String>getRideoffers(){return rideOffers;}

        public void setDriver(Driver driver) {
            this.driver=driver;
        }

        public Driver getDriver(){return driver;}



}
*/