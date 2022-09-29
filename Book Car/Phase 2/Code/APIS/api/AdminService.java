package com.example.Phase2.api;

import com.example.Phase2.Service.RideManager;
import com.example.Phase2.api.LogInAPI;
import com.example.Phase2.Service.UserManager;
import com.example.Phase2.model.*;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api/adminService")
@RestController

public class AdminService {


    @PostMapping("AcceptDriverRequest")
    public String AcceptDriver(@RequestBody AcceptDriverRequest newRequest){
        try {
            int driverId=newRequest.getDriverId();
            User targetAccount = UserManager.search(driverId);
            targetAccount.setStatus("Accepted");
            Driver driver = (Driver) targetAccount;
            driver.setAdmin((Admin) UserManager.search(newRequest.getAdminId()));
            UserManager.updateUser(targetAccount,driverId);
            return "Success Remove Driver Pandding";
        } catch (Exception error) {
            return error.getMessage();
        }
    }


    @GetMapping ("showAllDriverPandding")
    public ArrayList<User> ShowAllDriverPandding(){
        ArrayList<User> Users = UserManager.getSystemUsers();
        ArrayList<User>driverPandding=new ArrayList<>();
        for(int i=0;i<Users.size();i++) {
            User user = Users.get(i);
            if (user instanceof Driver && "pandding".equals(user.getStatus())) {
                driverPandding.add(user);
            }
        }
        return driverPandding;
    }

    @GetMapping ("showAllDrivers")
    public ArrayList<User> ShowAllDrivers() {
        ArrayList<User> Users = UserManager.getSystemUsers();
        ArrayList<User> drivers = new ArrayList<>();
        for (int i = 0; i < Users.size(); i++) {
            User user = Users.get(i);
            if (user instanceof Driver&&!"pandding".equals(user.getStatus()) && !"susspend".equals(user.getStatus())) {
                drivers.add(user);
            }
        }
        return drivers;
    }

    @PostMapping ("SuspendDriver")
    public String SuspendDriver(@RequestBody DriverID suspendDriver) throws Exception {
        try {
            int driverId = suspendDriver.getDriverId();
            User targetAccount = UserManager.search(driverId);
            targetAccount.setStatus("susspend");
            UserManager.updateUser(targetAccount, driverId);
            return "Succuss  to Suspend Driver";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @GetMapping ("showAllpassengers")
    public ArrayList<User> ShowAllpassengers() {
        ArrayList<User> Users = UserManager.getSystemUsers();
        ArrayList<User> passengers = new ArrayList<>();
        for (int i = 0; i < Users.size(); i++) {
            User user = Users.get(i);
            if (user instanceof Passenger && !"susspend".equals(user.getStatus())) {
                passengers.add(user);
            }
        }
        return passengers;
    }

    @PostMapping ("SuspendPassenger")
    public String SuspendPassenger(@RequestBody PassengerId suspendpassenger) throws Exception {
        try {
            int passengerId = suspendpassenger.getPassengerId();
            User targetAccount = UserManager.search(passengerId);
            targetAccount.setStatus("susspend");
            Passenger passenger = (Passenger) targetAccount;
            UserManager.updateUser(targetAccount, passengerId);
            return "Success  to susppend passenger";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("showAllRides")
    public ArrayList<Ride>allRides(){

        try {
            return RideManager.getRides();
        } catch (Exception ex) {
            return null;
        }
    }

    @PostMapping("selectRidetoShowEvents")
    public ArrayList<String> selectRide(@RequestBody RideId rideId){
        ArrayList<Ride> rides=RideManager.getRides();
        for (int i = 0; i < rides.size(); i++) {
            if (rides.get(i).getRideId() == rideId.getRideId()) {
                return  rides.get(i).getNotifications();

            }
        }
        return null;
    }

    @PostMapping("setDiscountArea")
    public String setDiscountArea(@RequestBody DiscountArea obj){

        Admin admin = new Admin();
        try {
            admin = (Admin) UserManager.search(obj.getAdminId());
        } catch (Exception ex) {
        }
        for (int i = 0; i < obj.getAreas().length; i++) {
            admin.setDiscountArea(obj.getAreas()[i]);
        }
        UserManager.updateUser(admin, obj.getAdminId());
        return "Success Add";
    }

}
