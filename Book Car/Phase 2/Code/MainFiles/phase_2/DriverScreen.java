package phase_2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverScreen implements Iscreen {

    public void showRides(ArrayList<Ride> ride) {

        for (Ride ride1 : ride) {
            if (!"Start".equals(ride1.getStatus()) && !"End".equals(ride1.getStatus())) {
                System.out.println("Ride Id: " + ride1.getRideId() + "  Source: " + ride1.getSource() + "  Destination: " + ride1.getDestination());
            }
        }

    }

    public void addNewAreaCase(int userId) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the favourite area in the following sequence (area1,area2)");
        String areas = input.next();
        String[] area = areas.split(",");
        Driver driver = null;
        for (String area1 : area) {
            try {
                driver = (Driver) UserManager.search(userId);
                driver.addFavouriteArea(area1);
            } catch (Exception ex) {
                System.out.println("No eXIst");
            }

        }
        UserManager.updateUser(driver, userId);

    }

    public void getRidesFromMySource(int userId) {

        Scanner input = new Scanner(System.in);

        try {

            Driver driver = (Driver) UserManager.search(userId);
            if (driver.getCurrentRideId() == -1) {
                ArrayList<String> favoriteArea = driver.getfavouruteArea();
                ArrayList<Ride> commonArea = RideManager.searchRide(favoriteArea);
                if (commonArea.isEmpty()) {
                    System.out.println("No exist any common area");
                } else {
                    showRides(commonArea);
                    System.out.println("If you will select Ride Enter(yes or no)");
                    String opinion = input.next();
                    if (opinion.equalsIgnoreCase("yes")) {
                        System.out.println("SelectOneRide..EnterRideId: ");
                        int rideId = input.nextInt();
                        System.out.println("Enter Ride Price: ");
                        String price = input.next();
                        driver.setcurrentRideId(rideId);
                        UserManager.updateUser(driver, userId);
                        Ride selectedRide = RideManager.searchById(rideId);

                        selectedRide.addToMap(userId, price);//in class Ride
                        selectedRide.addRideNotification("Captain name: " + driver.getUserName() + " put a price = " + price + " for the ride at " + java.time.LocalDateTime.now());

                        RideManager.updateRide(selectedRide, rideId);
                    }
                }
            } else {
                System.out.println("You can not select another ride before end the currnt ride");
            }
        } catch (Exception ex) {
            System.out.println("No Exist");
        }

    }

    public void rideNotification(int userId) {

        try {
            Driver driver = (Driver) UserManager.search(userId);

            if (driver.getCurrentRideId() != -1) {
                Ride ride = RideManager.searchById(driver.getCurrentRideId());

                if (driver.getUserId() == ride.getDriver().getUserId()) {

                    System.out.println("The Passenger accepted your offer:");
                    System.out.println("passenger MobilePhone: " + ride.getPassengersMobileAtRide()
                            + "  " + "Passenger Name :  " + ride.getPassengersNameAtRide());
                } else {

                    driver.setcurrentRideId(-1);
                    UserManager.updateUser(driver, userId);
                    System.out.println("THe Passenger not accpted the offer");
                }
            } else {

                System.out.println("No Exist new Notification");
            }

        } catch (Exception ex) {
            System.out.println("No Exist new Notification");
        }

    }

    //the finishCurrentRide not the cancel the ride that is the case the ride is start and finished
    public void finishCurrentRide(int userId) throws Exception {

        Driver driver = new Driver();
        try {

            driver = (Driver) UserManager.search(userId);
        } catch (Exception ex) {
            Logger.getLogger(DriverScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (driver.getCurrentRideId() != -1) {
            Ride currentRide = RideManager.searchById(driver.getCurrentRideId());
            //get the first passenger becuse that make ride request.
            Passenger[]passengers2=currentRide.getPassengers();
            Passenger passenger =passengers2[0];
            String price = currentRide.getRideoffers().get(userId);
            
            
            //update the driverwallet
            wallet driverWallet = driver.getWallet();
            driverWallet.deposit(Integer.parseInt(price));
            driver.setWallet(driverWallet);

            //withdraw from the passenger.
            wallet passengerWallet = passenger.getWallet();
            
            passengerWallet.withdrawal((int) (Integer.parseInt(price)-((Integer.parseInt(price))*currentRide.getDiscount())));
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
            } else {
                System.out.println("You can not finish the ride before start Ride");
            }
        } else {
            System.out.println("No exist Current Ride to finish it");
        }
    }

    public void showAllRating(int userId) {

        ArrayList<Ride> rides = RideManager.getRides();
        Driver driver=new Driver();
        boolean check = false;
        try {
            driver=(Driver)UserManager.search(userId) ;
        } catch (Exception ex) {
            Logger.getLogger(DriverScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Ride ride : rides) {

            if (ride.getDriver() != null && ride.getDriver().getUserId() == userId && "End".equals(ride.getStatus())) {

                System.out.println("Ride Id: " + ride.getRideId() + " PassengerRate: " + ride.getStar());
                check = true;
            }
        }
        if (!check) {
            System.out.println("No Exist Any Ride Rate");
        }else {System.out.println("ALL Average Rating: "+driver.getAverageRate());}

    }

    public void getCurrentBalance(int userId) {

        Driver driver = new Driver();

        try {

            driver = (Driver) UserManager.search(userId);
        } catch (Exception ex) {
            Logger.getLogger(DriverScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        wallet driverWallet = driver.getWallet();
        System.out.println("Current Balance is : " + driverWallet.getCurrentBalance());

    }

    @Override
    public void displayFeatures(int userId) {

        Scanner input = new Scanner(System.in);
        int choice = 1;
        while (choice != 7) {
            System.out.println("1-Add new Area\n" + "2-Get All Rides From My Source\n" + "3-RideNotification\n" + "4-FinishCurrentRide\n"
                    + "5-showAllRating\n" + "6-getCurrentBalance\n" + "7-logOut\n");

            choice = input.nextInt();
            switch (choice) {

                case 1: {
                    addNewAreaCase(userId);
                    break;
                }

                case 2: {
                    getRidesFromMySource(userId);
                    break;
                }
                case 3: {
                    rideNotification(userId);
                    break;
                }

                case 4: {

                    try {
                        finishCurrentRide(userId);
                    } catch (Exception ex) {
                        System.out.println("Not exist");
                    }
                    break;
                }

                case 5: {
                    showAllRating(userId);
                    break;
                }

                case 6: {

                    getCurrentBalance(userId);
                }
                case 7: {
                    try {
                        UserManager.changeStateForLogOut(userId);
                    } catch (Exception ex) {
                        System.out.println("Errorr");
                    }

                    break;
                }
                default: {
                    System.out.println("Please Enter the correct Choice");
                    break;
                }

            }

        }
    }
}
