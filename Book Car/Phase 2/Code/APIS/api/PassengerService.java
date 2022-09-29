package com.example.Phase2.api;

import com.example.Phase2.Service.RideManager;
import com.example.Phase2.Service.UserManager;
import com.example.Phase2.model.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


@RequestMapping("api/PassengerService")
@RestController

public class PassengerService {


    @PostMapping("RequestRide")
    public String RequestRide(@RequestBody RequestRide newRide) {
        String source = newRide.getSource();
        String destination = newRide.getDestination();
        int numberOfPassengers = newRide.getnumberOfPassengers();
        int userId = newRide.getPassengerId();
        String result = "";

        try {
            Passenger user = (Passenger) UserManager.search(userId);
            //Passenger user=new Passenger();
            if (user.getcurrentRideId() != -1) {
                return "Sorry you can not register new ride before end the current ride";
            } else {
                Ride ride = user.rideRequest(source, destination, numberOfPassengers);

                if (ifFirstRide(userId) || ifsameAreaDiscount(destination) || checkBirthday(user.getbirthday())) {

                    ride.setDiscount(0.1);
                    result = "The discount of that Ride is : 10%";
                } else if (numberOfPassengers == 1 || checkPublicHoliday()) {
                    ride.setDiscount(0.05);
                    result = "The discount of that Ride is : 5%";
                }

                ride.addPassengerToTheRide(user, 0);
                int rideId = RideManager.addNewRide(ride);
                user.setcurrentRideId(rideId);
                UserManager.updateUser(user, userId);

                if (numberOfPassengers != 0) {
                    int[] accIds = newRide.getpassengers();
                    for (int i = 0; i < accIds.length; i++) {
                        Passenger p = (Passenger) UserManager.search(accIds[i]);
                        ride.addPassengerToTheRide(p, i + 1);
                        p.setcurrentRideId(rideId);
                        UserManager.updateUser(p, p.getUserId());
                    }
                }
                RideManager.updateRide(ride, rideId);
                result += " ,Success Request";
                return result;
            }
        } catch (Exception error) {
            System.out.println("NO EXIST THAT PASSENGER");
        }
        return null;

    }


    public boolean ifFirstRide(int userId) {

        ArrayList<Ride> rides = RideManager.getRides();
        for (int i = 0; i < rides.size(); i++) {
            Passenger[] passengers = rides.get(i).getPassengers();
            if (passengers[0].getUserId() == userId) {
                return false;
            }

        }
        return true;
    }

    public boolean checkPublicHoliday() {

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        if ((month == 12 && day == 31) || (month == 1 && day == 25) || (month == 5 && day == 2)) {
            return true;
        }
        return false;
    }

    private boolean checkBirthday(String birthdayDate) {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        String[] save=birthdayDate.split(",");
        if (month==Integer.parseInt(save[0])&&day==Integer.parseInt(save[1])) {
            return true;
        }
        return false;

    }

    public boolean ifsameAreaDiscount(String destination) {

        ArrayList<Admin> admins = UserManager.getSystemAdmin();

        for (int i = 0; i < admins.size(); i++) {
            Admin admin = admins.get(i);
            ArrayList<String> area = admin.getDicountArea();
            for (String area1 : area) {

                if (area1 == null ? destination == null : area1.equals(destination)) {
                    return true;
                }
            }
        }
        return false;
    }


    @PostMapping("showAllRideOffers")
    public Map<Integer, String> showAllRideOffers(@RequestBody PassengerId obj) {
        try {
            Passenger passenger = (Passenger) UserManager.search(obj.getPassengerId());
            int rideId = passenger.getcurrentRideId();

            if (rideId != -1) {
                Ride currentRide = RideManager.search(rideId, obj.getPassengerId());

                if (!currentRide.getRideoffers().isEmpty()) {

                    if (("Start".equals(currentRide.getStatus()))) {
                        return null;
                    } else {
                        return currentRide.getRideoffers();
                    }
                }
                //return null;
            }
        } catch (Exception error) {
        }
        return null;
    }

    @PostMapping("SelectDriverOffer")
    public String SelectDriverOffer(@RequestBody RideSelection obj) {
        try {
            Passenger passenger = (Passenger) UserManager.search(obj.getPassengerId());
            int rideId = passenger.getcurrentRideId();
            Ride currentRide = RideManager.search(rideId, obj.getPassengerId());

            int driverId = obj.getDriverId();
            String ridePrice = currentRide.getRideoffers().get(driverId);

            int userId = obj.getPassengerId();

            if (checkPassengerBalance(ridePrice, userId)) {
                try {

                    currentRide.setDriver((Driver) UserManager.search(driverId));
                    Driver driver = (Driver) UserManager.search(driverId);
                    currentRide.setStatus("Start");
                    RideManager.updateRide(currentRide, rideId);
                    currentRide.addRideNotification("Passenger Name :  " + currentRide.getPassengersNameAtRide() + " accepted the offer of captain " + driver.getUserName() + " at " + java.time.LocalDateTime.now());
                    currentRide.setArrivedTimeToUser(java.time.LocalDateTime.now());
                    currentRide.addRideNotification("Captain " + driver.getUserName() + " arrived to " + currentRide.getPassengersNameAtRide() + "'s source location at " + currentRide.getArrivedTimeToUser());

                    return "Success Select";
                } catch (Exception error) {
                    return "The driver ID not correct try Again";

                }
            } else {
                currentRide.setStatus("deleted");

                for (int i = 0; i < currentRide.getPassengers().length; i++) {

                    passenger = currentRide.getPassengers()[i];
                    passenger.setcurrentRideId(-1);
                    UserManager.updateUser(passenger, passenger.getUserId());
                }
                RideManager.updateRide(currentRide, rideId);
                return "Sorry, wallet balance not enough, set to wallet and try again ";
            }
        } catch (Exception error) {
            return "The Info not correct try Again";
        }
    }


    private boolean checkPassengerBalance(String ridePrice, int userId) {
        Passenger passenger=new Passenger();
        try {

            passenger=(Passenger)UserManager.search(userId);
        } catch (Exception ex) {
            //Logger.getLogger(DriverScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        wallet driverWallet= passenger.getWallet();
        return driverWallet.getCurrentBalance()>=Integer.parseInt(ridePrice);

    }

    @PostMapping("EvaluateRide")
    public String EvaluateRide (@RequestBody EvaluateRide evaluateride) throws Exception
    {    try{
        Ride ride = (Ride ) RideManager.searchById(evaluateride.getRideId());

        if (ride.getPassengers()[0].getUserId() == evaluateride.getPassengerId() && "End".equals(ride.getStatus())) {

            if (!(evaluateride.getStar()>= 1 && evaluateride.getStar() <= 5)) {
                return "The star Number not correct try Again";
            } else {
                ride.setStar(evaluateride.getStar());
                RideManager.updateRide(ride, evaluateride.getRideId());
                return "Success Evaluate Ride";
            }

        } else {

            return "You can not evaluate this ride before the ride start ";
        }
    } catch (Exception e) {
        return e.getMessage();
    }
    }


    @PostMapping("Desposit")
    public String desposit(@RequestBody Desposit obj){

        int userId= obj.getPassengerId();
        int despositValue=obj.getDespositValue();
        Passenger passenger=new Passenger();

        try {

            passenger=(Passenger)UserManager.search(userId);
        } catch (Exception ex) {
            //Logger.getLogger(DriverScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        //update the driverwallet
        wallet driverWallet= passenger.getWallet();
        driverWallet.deposit(despositValue);
        passenger.setWallet(driverWallet);

        UserManager.updateUser(passenger, userId);
        return "Success desposit";
    }

    @PostMapping("getCurrentBalance")
    public String getCurrentBalance(@RequestBody PassengerId obj){
        Passenger passenger=new Passenger();
        int userId= obj.getPassengerId();

        try {

            passenger=(Passenger)UserManager.search(userId);
        } catch (Exception ex) {
            //Logger.getLogger(DriverScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        wallet driverWallet= passenger.getWallet();
        return "Current Balance is : "+driverWallet.getCurrentBalance();
    }


}






 

