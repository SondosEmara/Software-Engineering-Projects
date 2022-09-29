package com.example.Phase2.Service;

import com.example.Phase2.model.Admin;
import com.example.Phase2.model.Passenger;
import com.example.Phase2.model.User;

import com.example.Phase2.model.Driver;
import java.util.ArrayList;


public  class UserManager {

    private static int usersNumber = 0;
    private static ArrayList<User> systemUsers = new ArrayList<>();

    public static ArrayList<Admin> getSystemAdmin() {

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

    public static boolean addNewUser(User newUser) {

            try {
                if (newUser instanceof Passenger) {
                    newUser.setStatus("Active");
                } else if (newUser instanceof Driver) {
                    newUser.setStatus("pandding");
                }
                newUser.setUserId(++usersNumber);
                systemUsers.add(newUser);
                return true;
            }catch(Exception error){return false;}

    }


    public static boolean addNewAdmin(User newAdmin) {
        try{
            newAdmin.setUserId(++usersNumber);
            systemUsers.add(newAdmin);
            return true;
        }
        catch (Exception ex){
            return false;
        }


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

/*
public  class UserManager {

    private static int usersNumber = 0;
    private static ArrayList<User> systemUsers = new ArrayList<>();

    public UserManager() {
        systemUsers = new ArrayList<>();
        usersNumber = 0;
    }

    public static User searchForLogIn(String userName, String password) throws Exception {

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
        } else if ("Susspend".equals(targetUser.getStatus())) {
            throw new Exception("your user is susupend ");
        }

        targetUser.setStatus("Online");
        systemUsers.set(i, targetUser);
        return targetUser;
    }

    public static boolean newRegister(User newUser) {

        try {
            if (newUser instanceof Passenger) {
                newUser.setStatus("Active");
            } else if (newUser instanceof Driver) {
                newUser.setStatus("pandding");
            }
            newUser.setUserId(++usersNumber);
            systemUsers.add(newUser);
            return true;
        }catch(Exception error){return false;}

    }

    public static boolean addNewAdmin(User newAdmin) {
        try{
            newAdmin.setUserId(++usersNumber);
            systemUsers.add(newAdmin);
            return true;
        }
        catch (Exception ex){
            return false;
        }


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

    public static void logOut(int userId) throws Exception {

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
                //System.out.println("Passenger id :" + systemUser.getUserId() + ((Passenger) systemUser).getcurrentRideId());
            } else if (systemUser instanceof Driver) {
                Driver driver = (Driver) systemUser;
                System.out.println("Driver:" + driver.getStatus() + driver.getUserId());
            }
        }
    }

}*/

