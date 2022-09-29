package phase_2;

import java.time.LocalDate;

import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PassengerScreen implements Iscreen {

    public void showAllRideOffers(Map<Integer, String> rideOffers) throws Exception {

        if (!rideOffers.isEmpty()) {
            System.out.println("NEW NOTIFICATION :");
            for (Map.Entry<Integer, String> offer : rideOffers.entrySet()) {

                try {
                    Driver driver = (Driver) UserManager.search(offer.getKey());

                    System.out.println("The Driver Id : " + offer.getKey()
                            + " Price :" + offer.getValue() + " driverAverageRating: " + driver.getAverageRate());
                } catch (Exception error) {
                    throw new Exception("No Exist");
                }
            }
        } else {
            throw new Exception("no Exist any Notifiaction");
        }

    }

    public void requestRide(int userId) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the source Ride");
        String source = input.next();
        System.out.println("Enter the destination Ride");
        String destination = input.next();
        System.out.println("Enter number of passenger (not count you)");
        int numberOfPassengers = input.nextInt();

        try {

            Passenger user = (Passenger) UserManager.search(userId);
            //Passenger user=new Passenger();
            if (user.getcurrentRideId() != -1) {
                System.out.println("Sorry you can not register new ride before end the current ride");
            } else {
                Ride ride = user.rideRequest(source, destination, numberOfPassengers);

                
                if (ifFirstRide(userId) || ifsameAreaDiscount(destination) || checkBirthday(user.getbirthday())) {

                    ride.setDiscount(0.1);
                    System.out.println("The discount of that Ride is : 10%");
                } else if (numberOfPassengers == 1 || checkPublicHoliday()) {
                    ride.setDiscount(0.05);
                    System.out.println("The discount of that Ride is : 5%");
                }

                ride.addPassengerToTheRide(user, 0);
                int rideId = RideManager.addNewRide(ride);
                user.setcurrentRideId(rideId);
                UserManager.updateUser(user, userId);

                if (numberOfPassengers != 0) {

                    System.out.println("Enter the passenger ids: ");
                    int[] accIds = new int[numberOfPassengers];
                    for (int i = 0; i < numberOfPassengers; i++) {
                        accIds[i] = input.nextInt();
                        Passenger p = (Passenger) UserManager.search(accIds[i]);
                        ride.addPassengerToTheRide(p, i + 1);
                        p.setcurrentRideId(rideId);
                        UserManager.updateUser(p, p.getUserId());
                    }
                }

                RideManager.updateRide(ride, rideId);
            }
        } catch (Exception error) {
            System.out.println("NO EXIST THAT PASSENGER");
        }

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

    public void rideNotification(int userId) {
        Scanner input = new Scanner(System.in);
        try {
            Passenger passenger = (Passenger) UserManager.search(userId);
            int rideId = passenger.getcurrentRideId();
            if (rideId != -1) {
                Ride currentRide = RideManager.search(rideId, userId);

                if (!currentRide.getRideoffers().isEmpty()) {

                    if (("Start".equals(currentRide.getStatus()))) {
                        System.out.println("No exist Any Notification");
                    } else {
                        try {

                            showAllRideOffers(currentRide.getRideoffers());

                            System.out.println("if you need to seclect Driver (yes or no)");
                            String opinion = input.next();
                            if ("yes".equalsIgnoreCase(opinion)) {
                                System.out.println("Select One Driver Id");
                                int driverId = input.nextInt();
                                String ridePrice = currentRide.getRideoffers().get(driverId);

                                if (checkPassengerBalance(ridePrice, userId)) {
                                    try {

                                        currentRide.setDriver((Driver) UserManager.search(driverId));
                                        Driver driver = (Driver) UserManager.search(driverId);
                                        currentRide.setStatus("Start");
                                        RideManager.updateRide(currentRide, rideId);
                                        currentRide.addRideNotification("Passenger Name :  " + currentRide.getPassengersNameAtRide() + " accepted the offer of captain " + driver.getUserName() + " at " + java.time.LocalDateTime.now());
                                        currentRide.setArrivedTimeToUser(java.time.LocalDateTime.now());
                                        currentRide.addRideNotification("Captain " + driver.getUserName() + " arrived to " + currentRide.getPassengersNameAtRide() + "'s source location at " + currentRide.getArrivedTimeToUser());

                                    } catch (Exception error) {
                                        System.out.println("The driver ID not correct try Again");

                                    }
                                } else {
                                    currentRide.setStatus("deleted");

                                    for (int i = 0; i < currentRide.getPassengers().length; i++) {

                                        passenger = currentRide.getPassengers()[i];
                                        passenger.setcurrentRideId(-1);
                                        UserManager.updateUser(passenger, passenger.getUserId());
                                    }
                                    RideManager.updateRide(currentRide, rideId);
                                    System.out.println("Sorry, wallet balance not enough, set to wallet and try again ");
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());

                        }
                    }
                } else {
                    System.out.println("No exist any new Notifiacation");
                }
            } else {
                System.out.println("No exist any new Ride");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

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

    public void evaluateRide(int userId) throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.println("Evalute the  driver ride from current Ride:");
        System.out.println("ENter the rideId");

        int rideId = input.nextInt();
        Ride ride = RideManager.searchById(rideId);
        if (ride.getPassengers()[0].getUserId() == userId && "End".equals(ride.getStatus())) {
            System.out.println("Enter the StarNumber but between 1 to 5 to evaluate");
            int starNum = input.nextInt();

            if (!(starNum >= 1 && starNum <= 5)) {
                System.out.println("The star Number not correct try Again");

            } else {
                ride.setStar(starNum);
                RideManager.updateRide(ride, rideId);
            }

        } else {

            System.out.println("You can not evaluate this ride before the ride start ");
        }
    }

    public void desposit(int userId) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the despositValue");
        int despositValue = input.nextInt();
        Passenger passenger = new Passenger();

        try {

            passenger = (Passenger) UserManager.search(userId);
        } catch (Exception ex) {
            Logger.getLogger(DriverScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        //update the driverwallet
        wallet driverWallet = passenger.getWallet();
        driverWallet.deposit(despositValue);
        passenger.setWallet(driverWallet);

        UserManager.updateUser(passenger, userId);
    }

    public void getCurrentBalance(int userId) {

        Passenger passenger = new Passenger();

        try {

            passenger = (Passenger) UserManager.search(userId);
        } catch (Exception ex) {
            Logger.getLogger(DriverScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        wallet driverWallet = passenger.getWallet();
        System.out.println("Current Balance is : " + driverWallet.getCurrentBalance());

    }

    private boolean checkPassengerBalance(String ridePrice, int userId) {
        Passenger passenger = new Passenger();

        try {

            passenger = (Passenger) UserManager.search(userId);
        } catch (Exception ex) {
            Logger.getLogger(DriverScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        wallet driverWallet = passenger.getWallet();
        return driverWallet.getCurrentBalance() >= Integer.parseInt(ridePrice);

    }

    @Override
    public void displayFeatures(int userId) {
        Scanner input = new Scanner(System.in);
        int choice = 1;
        while (choice != 6) {
            System.out.println("1-RequestRide\n" + "2-RideNotification\n" + "3-Evalute any Ride you do it\n"
                    + "4-Desposit\n" + "5-getCurrentBalance\n" + "6-logOut\n");

            choice = input.nextInt();
            switch (choice) {

                case 1: {
                    requestRide(userId);
                    break;
                }

                case 2: {

                    rideNotification(userId);
                    break;
                }
                case 3: {
                    try {
                        evaluateRide(userId);
                    } catch (Exception ex) {
                        System.out.println("Not Exist");
                    }
                    break;
                }

                case 4: {

                    desposit(userId);
                    break;
                }

                case 5: {
                    getCurrentBalance(userId);
                    break;
                }

                case 6: {
                    try {
                        UserManager.changeStateForLogOut(userId);
                    } catch (Exception ex) {
                        System.out.println("Errorr");
                    }
                    break;
                }
                default: {
                    System.out.println("please enter the correct choice try Again");
                }

            }
        }
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

}
