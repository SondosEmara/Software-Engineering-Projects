package phase_2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminScreen implements Iscreen {

    public void showDriverPendding() throws Exception {

        boolean check = false;
        ArrayList<User> users = UserManager.getSystemUsers();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user instanceof Driver && "pandding".equals(user.getStatus())) {
                Driver driver = (Driver) user;
                System.out.println(
                        i + "-" + "DriverId: " + driver.getUserId() + " , "
                        + "UserName: " + driver.getUserName() + " , "
                        + "LisenceId: " + driver.getDriverLicense() + " , "
                        + "NationalId: " + driver.getNationalId() + " , ");
                check = true;
            }

        }
        if (!check) {
            throw new Exception("NOt exists Any User");
        }
    }

    public static void showDriverUsers() throws Exception {

        boolean check = false;
        ArrayList<User> users = UserManager.getSystemUsers();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user instanceof Driver && !"pandding".equals(user.getStatus()) && !"susspend".equals(user.getStatus())) {
                Driver driver = (Driver) user;
                System.out.println(
                        i + "-" + "DriverId: " + driver.getUserId() + " , "
                        + "UserName: " + driver.getUserName() + " , "
                        + "LisenceId: " + driver.getDriverLicense() + " , "
                        + "NationalId: " + driver.getNationalId() + " , ");
                check = true;
            }
        }
        if (!check) {
            throw new Exception("NOt exists Any User");
        }
    }

    public void showPassengerUsers() throws Exception {
        boolean check = false;
        ArrayList<User> users = UserManager.getSystemUsers();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user instanceof Passenger && !"susspend".equals(user.getStatus())) {
                Passenger passenger = (Passenger) user;
                System.out.println(
                        i + "-" + "UserId: " + passenger.getUserId() + " , "
                        + "UserName: " + passenger.getUserName() + " , "
                        + "Emial: " + passenger.getEmail() + " , ");
                check = true;
            }
        }
        if (!check) {
            throw new Exception("NOt exists Any User");
        }

    }

    public void discountArea(int userId) {

        Scanner input = new Scanner(System.in);
        Admin admin = new Admin();
        try {
            admin = (Admin) UserManager.search(userId);
        } catch (Exception ex) {
            Logger.getLogger(AdminScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] areas;
        System.out.println("Do you add a new Area Discount (Yes) or (No)?");
        String opinion = input.next();

        if ("yes".equalsIgnoreCase(opinion)) {
            System.out.println("Enter the area name  by the following syntax 'area1,area2'");
            String areaInput = input.next();
            areas = areaInput.split(",");
            for (int i = 0; i < areas.length; i++) {
                admin.setDiscountArea(areas[i]);
            }
        }
        UserManager.updateUser(admin, userId);
    }

    public void commonFunction(String newState, int userId) {

        String userIds;
        String[] accIds;
        Scanner input = new Scanner(System.in);
        System.out.println("if you are select user if that (yes) or (no)");
        String opinion = input.next();
        if ("yes".equalsIgnoreCase(opinion)) {
            System.out.println("Enter the user id  by the following syntax 'userId1,userId2'");
            userIds = input.next();

            accIds = userIds.split(",");
            for (String accId : accIds) {
                try {
                    User targetUser = UserManager.search(Integer.parseInt(accId));
                    if ("Accepted".equals(newState)) {
                        targetUser.setStatus("Accepted");
                        Driver driver = (Driver) targetUser;
                        driver.setAdmin((Admin) UserManager.search(userId));
                    } else if ("Susspend".equals(newState)) {
                        targetUser.setStatus("susspend");
                    }
                    UserManager.updateUser(targetUser, Integer.parseInt(accId));
                } catch (Exception error) {

                    System.out.println(error.getMessage());
                }
            }

        }
    }

    public void showRideEvent(ArrayList<Ride> rides, int id) {
        for (int i = 0; i < rides.size(); i++) {
            if (rides.get(i).getRideId() == id) {
                for (int j = 0; j < rides.get(i).getNotifications().size(); j++) {
                    System.out.println(rides.get(i).getNotifications().get(j));
                }
                break;
            }
        }
    }

    @Override
    public void displayFeatures(int userId
    ) {

        Scanner input = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            System.out.println("1-ShowDriverPendding\n" + "2-suspendDriverUser\n" + "3-suspendPassengerUser\n"
                    + "4-show Ride Events\n" + "5-setDiscountArea\n" +"6-getDiscountArea\n"+ "7-logOut\n");
            choice = input.nextInt();
            switch (choice) {
                case 1: {
                    try {
                        showDriverPendding();
                        commonFunction("Accepted", userId);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                    break;
                }
                case 2: {
                    try {
                        showDriverUsers();
                        commonFunction("Susspend", userId);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                    break;
                }

                case 3: {
                    try {
                        showPassengerUsers();
                        commonFunction("Susspend", userId);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                    break;

                }

                case 4: {
                    try {
                        ArrayList<Ride> rides = RideManager.getRides();
                        if (rides.isEmpty()) {
                            System.out.println("There is no rides now!");
                            break;
                        }
                        for (int i = 0; i < rides.size(); i++) {
                            if (!"deleted".equals(rides.get(i).getStatus())) {
                                System.out.println("Ride ID: " + rides.get(i).getRideId() + " Source Location " + rides.get(i).getSource() + " destination Location " + rides.get(i).getDestination());
                            }
                        }
                        System.out.println("Enter Ride ID to show its details: ");
                        int id = input.nextInt();
                        showRideEvent(rides, id);
                    } catch (Exception ex) {
                        System.out.println("Errrorrr");
                    }
                    break;

                }
                case 5: {
                    discountArea(userId);
                    break;
                }
                case 6:{
                    getDiscountArea(userId);
                    break;
                
                }
                case 7: {
                    try {
                        UserManager.changeStateForLogOut(userId);
                    } catch (Exception ex) {
                        System.out.println("Errrorrr");
                    }
                    break;
                }
                default:
                    break;
            }
        }

    }
    public  void getDiscountArea(int userId) {
        
        try {
            Admin admin =(Admin) UserManager.search(userId);
            ArrayList<String>areas=admin.getDicountArea();
            for(int i=0;i<areas.size();i++){
                System.out.println(areas.get(i));
            
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

}
