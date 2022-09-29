package phase_2;


import java.util.ArrayList;

public abstract class User {

    private String userName;
    private String email;
    private String mobileNum;
    private String password;
    private String status;
    private Iscreen userScreen;
    private  int userId ;
   
    public User() {
       
    }
    public void  setUserId(int userId){this.userId=userId;}
    public int getUserId(){return userId;}

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNum() {
        return this.mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    void setScreenType(Iscreen screenType) {
        userScreen = screenType;
    }

    void openScreen() {
       this.userScreen.displayFeatures(userId);
    }


 
}

class Admin extends User {
    
    private ArrayList<String>discountArea=new ArrayList<>();
    public ArrayList<String>getDicountArea(){return discountArea;}
    public void setDiscountArea(String newArea){discountArea.add(newArea);}
    
}

class Passenger extends User {
    private int currentRideId=-1;
    private wallet passengerWallet=new wallet();
    private String birthdayDate;
    
    public wallet getWallet(){
      return passengerWallet;
    }
    public void setWallet(wallet walletdriver){
      this.passengerWallet=walletdriver;
    }
    Ride rideRequest(String source, String destination, int numberOfPassengers) {
        Ride newRide=new Ride();
        newRide.setDestination(destination);
        newRide.setSource(source);
        //newRide.addPassengerToTheRide(this);
        newRide.setNumberOfPassengers(numberOfPassengers);
        newRide.setStatus("Waiting");
        return newRide;
        
    }

    void setcurrentRideId(int currentRideId){this.currentRideId=currentRideId;}
    int getcurrentRideId(){return currentRideId;}
    
    String getbirthday(){return birthdayDate;}
    void setBirthday(String birthdayDate ){this.birthdayDate=birthdayDate;}
}

class Driver extends User {

    private String drivingLicense;
    private String nationalId;
    private Admin admin;
    private double averageRate=0;
    private  ArrayList<String> favoriteArea=new ArrayList<>();
    private int currentRideId=-1;
    private wallet driverWallet=new wallet();
    
    
    public wallet getWallet(){
      return driverWallet;
    }
    public void setWallet(wallet walletdriver){
      this.driverWallet=walletdriver;
    }
    
    public void setAdmin(Admin admin){this.admin=admin;}

    public void setcurrentRideId(int currentRideId){
      this.currentRideId=currentRideId;
    }
    public int getCurrentRideId() {
        return currentRideId;
    }
    public String getNationalId() {
        return nationalId;
    }

    public String getDriverLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Admin getAdmin() {
        return admin;
    }
    
    public double getAverageRate() {
        
        ArrayList<Ride>rides=RideManager.getRides();
        
        int counter=0;double rate=0;
        for (Ride ride : rides) {
          
           if(ride.getDriver()!=null&&ride.getDriver().getUserId()==this.getUserId()){
            rate+=ride.getStar();
            counter++;
           }
        }
        if(counter==0) return 0;
        averageRate=rate/counter;
        return averageRate;
    }
    public void addFavouriteArea(String area){
        favoriteArea.add(area);
    }
    
    public ArrayList<String>getfavouruteArea(){return favoriteArea;}

}
