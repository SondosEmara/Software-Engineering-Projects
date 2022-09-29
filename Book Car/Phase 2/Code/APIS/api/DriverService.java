package com.example.Phase2.api;

import com.example.Phase2.Service.RideManager;
import com.example.Phase2.Service.UserManager;
import com.example.Phase2.model.*;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api/DriverService")
@RestController

public class DriverService {


    @PostMapping("AddfavouriteArea")
    String AddfavouriteArea(@RequestBody AddNewArea newArea){
        try {
            Driver driver = (Driver) UserManager.search(newArea.getDriverId());
            String[]area=newArea.getArea();
            for(int i=0;i<area.length;i++) {
                driver.addFavouriteArea(area[i]);
            }
            UserManager.updateUser(driver, newArea.getDriverId());
            return "Success Add new Area";
        } catch (Exception ex) {
            return "cant add new Area ,Try Again";
        }
    }


    @PostMapping ("SelectRide")
    String SelectRide(@RequestBody DriverSelectRide object) {
        try {

            int userId = object.getDriverId();
            Driver driver = (Driver) UserManager.search(userId);
            if (driver.getCurrentRideId() == -1) {
                ArrayList<String> favoriteArea = driver.getfavouruteArea();
                ArrayList<Ride> commonArea = RideManager.searchRide(favoriteArea);
                if (commonArea.isEmpty()) {
                    return "No exist any common area";
                } else {
                    int rideId = object.getRideId();

                    String price = object.getPrice();
                    driver.setcurrentRideId(rideId);
                    UserManager.updateUser(driver, userId);
                    Ride selectedRide = RideManager.searchById(rideId);

                    selectedRide.addToMap(userId, price);//in class Ride
                    selectedRide.addRideNotification("Captain name: " + driver.getUserName() + " put a price = " + price + " for the ride at " + java.time.LocalDateTime.now());

                    RideManager.updateRide(selectedRide, rideId);
                    return "Success";
                }
            }else return "You can not select another ride before end the current ride";
        } catch (Exception e) {}


    return null;
    }


    @PostMapping("rideNotification")
    String rideNotification(@RequestBody DriverID obj){
        try {
            Driver driver = (Driver) UserManager.search(obj.getDriverId());

            if (driver.getCurrentRideId() != -1) {
                Ride ride = RideManager.searchById(driver.getCurrentRideId());

                if (driver.getUserId() == ride.getDriver().getUserId()) {

                    return "The Passenger accepted your offer:\n"+"passenger MobilePhone: " + ride.getPassengersMobileAtRide()
                            + "  " + "Passenger Name :  " + ride.getPassengersNameAtRide();
                } else {

                    driver.setcurrentRideId(-1);
                    UserManager.updateUser(driver, obj.getDriverId());
                    return "THe Passenger not accepted the offer";
                }
            } else {

                return "No Exist new Notification";
            }

        } catch (Exception ex) {
           return "No Exist new Notification";
        }

    }


    @PostMapping ("GetAllRidesFromMySource")
    ArrayList<Ride> GetAllRidesFromMySource(@RequestBody DriverID driverID) {
        try {
            Driver driver = (Driver) UserManager.search(driverID.getDriverId());
            ArrayList<Ride> commonArea=null;
            if (driver.getCurrentRideId() == -1) {
                ArrayList<String> favoriteArea = driver.getfavouruteArea();
                commonArea = RideManager.searchRide(favoriteArea);
            }
            return commonArea;
        }
        catch(Exception error){
            return null;
        }
    }


    @PostMapping("FinishCurrentRide")
    String FinishCurrentRide(@RequestBody DriverID finish) {
        {
            Driver driver = new Driver();
            int userId = finish.getDriverId();
            try {

                driver = (Driver) UserManager.search(userId);
            } catch (Exception ex) {
            }

            if (driver.getCurrentRideId() != -1) {
                Ride currentRide = null;
                try {
                    currentRide = RideManager.searchById(driver.getCurrentRideId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //get the first passenger because that make ride request.
                Passenger[] passengers2 = currentRide.getPassengers();
                Passenger passenger = passengers2[0];
                String price = currentRide.getRideoffers().get(userId);


                //update the driverwallet
                wallet driverWallet = driver.getWallet();
                driverWallet.deposit(Integer.parseInt(price));
                driver.setWallet(driverWallet);

                //withdraw from the passenger.
                wallet passengerWallet = passenger.getWallet();

                passengerWallet.withdrawal((int) (Integer.parseInt(price) - ((Integer.parseInt(price)) * currentRide.getDiscount())));
                passenger.setWallet(passengerWallet);

                UserManager.updateUser(passenger, passenger.getUserId());


                if ("Start".equals(currentRide.getStatus())) {

                    Passenger[] passengers = currentRide.getPassengers();
                    driver.setcurrentRideId(-1);
                    currentRide.setStatus("End");
                    currentRide.setArrivedTimeToDestination(java.time.LocalDateTime.now());
                    currentRide.addRideNotification("Captain " + driver.getUserName() + " arrived to " + currentRide.getPassengersNameAtRide() + "'s destination location at " + currentRide.getArrivedTimeToDestination());
                    currentRide.setDriver(driver);
                    for (int i = 0; i < passengers.length; i++) {
                        passengers[i].setcurrentRideId(-1);
                        //currentRide.addPassengerToTheRide(passengers[i]);
                        UserManager.updateUser(passengers[i], passengers[i].getUserId());
                    }
                    RideManager.updateRide(currentRide, currentRide.getRideId());
                    UserManager.updateUser(driver, driver.getUserId());
                    return "Success FinishCurrentRide";
                } else {
                    return "You can not finish the ride before start Ride";
                }
            } else {
                return "No exist Current Ride to finish it";
            }
        }
    }


    @PostMapping("ShowAllRatings")
    String ShowAllRatings(@RequestBody DriverID showAllRatings) throws Exception {
        Driver driver = (Driver) UserManager.search(showAllRatings.getDriverId());
        ArrayList<Ride> rides = RideManager.getRides();
        boolean check = false;
        String result="";
        for (Ride ride : rides) {
            if (ride.getDriver() != null && ride.getDriver().getUserId() == driver.getUserId() && "End".equals(ride.getStatus())) {
                result+="Ride Id: " + ride.getRideId() + " PassengerRate: " + ride.getStar()+"\n";
                check = true;
            }
        }
        if (!check) {
            return "No Exist Any Ride Rate";
        }else {return result+="\nALL Average Rating: "+driver.getAverageRate();}
    }


    @PostMapping ("getCurrentBalance")
     String getCurrentBalance(@RequestBody DriverID obj){

        Driver driver = new Driver();
        int userId=obj.getDriverId();

        try {

            driver = (Driver) UserManager.search(userId);
        } catch (Exception ex) {
        }

        wallet driverWallet = driver.getWallet();
        return "Current Balance is : " + driverWallet.getCurrentBalance();
    }
}
