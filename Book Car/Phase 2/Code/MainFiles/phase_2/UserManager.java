package phase_2;

import java.util.ArrayList;
import java.util.Scanner;

public  class UserManager {

    private static int usersNumber = 0;
    private static ArrayList<User> systemUsers = new ArrayList<>();

    static ArrayList<Admin> getSystemAdmin() {
        
        ArrayList<Admin>admin=new ArrayList<>();
        for (User systemUser : systemUsers) {
            if(systemUser instanceof Admin){admin.add((Admin)systemUser);}
        }
        
        return admin;
        
        
    }

    public UserManager() {
        systemUsers = new ArrayList<>();
        usersNumber = 0;
    }

    public static User searchForlogIn(String userName, String password) throws Exception {

        User targetUser = null;
        int i;
        for (i = 0; i < systemUsers.size(); i++) {
            if ((systemUsers.get(i).getUserName().equals(userName)) && (systemUsers.get(i).getPassword().equals(password))) {
                targetUser = systemUsers.get(i);
                break;
            }
        }

        if (targetUser == null) {

            throw new Exception("the info not correct try again");
        } else if ("pandding".equals(targetUser.getStatus())) {
            throw new Exception("you are now in pandding State");
        } else if ("susspend".equals(targetUser.getStatus())) {
            throw new Exception("your user is susupend ");
        }

        targetUser.setStatus("Online");
        systemUsers.set(i, targetUser);
        return targetUser;
    }

    public static void addNewUser(Iregister registerType) {

        String email = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Enter userName:");
        String userName = input.next();
        System.out.println("The email is not mandratory:Enter yes TO enter or no:");
        String opinion = input.next();

        if ("yes".equalsIgnoreCase(opinion)) {
            System.out.println("Enter email:");
            email = input.next();
        }

        System.out.println("Enter mobilPhone:");
        String mobilPhone = input.next();
        System.out.println("Enter password:");
        String password = input.next();

        User newUser = registerType.registerLogic(userName, email, mobilPhone, password);
        if (newUser instanceof Passenger) {
            newUser.setScreenType(new PassengerScreen());
        } else if (newUser instanceof Driver) {
            newUser.setScreenType(new DriverScreen());
        }
        newUser.setUserId(++usersNumber);
        systemUsers.add(newUser);
    }

    public static void addNewAdmin(User newAdmin) {
        newAdmin.setUserId(++usersNumber);
        newAdmin.setScreenType(new AdminScreen());
        systemUsers.add(newAdmin);

    }

    public static ArrayList<User> getSystemUsers() {
        return systemUsers;
    }

    public static User search(int userId) throws Exception {
        for (int i = 0; i < systemUsers.size(); i++) {
            if (systemUsers.get(i).getUserId() == userId) {
                return systemUsers.get(i);
            }
        }
        throw new Exception("The user id is not correct");
    }

    public static void updateUser(User targetUser, int userId) {

        for (int i = 0; i < systemUsers.size(); i++) {
            if (systemUsers.get(i).getUserId() == userId) {
                systemUsers.set(i, targetUser);
            }
        }

    }

    public static void changeStateForLogOut(int userId) throws Exception {

        User user = search(userId);
        user.setStatus("Offline");
        updateUser(user, userId);
    }

    //to only test
    public static void print() {

        for (User systemUser : systemUsers) {

            if (systemUser instanceof Admin) {
                System.out.println("Admin:" + systemUser.getStatus());

            } else if (systemUser instanceof Passenger) {
                System.out.println("Passenger id :" + systemUser.getUserId() + ((Passenger) systemUser).getcurrentRideId());
            } else if (systemUser instanceof Driver) {
                Driver driver = (Driver) systemUser;
                System.out.println("Driver:" + driver.getStatus() + driver.getUserId());
            }
        }
    }
}