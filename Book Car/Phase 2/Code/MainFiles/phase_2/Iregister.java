package phase_2;


import java.util.Scanner;



interface Iregister {

    public User registerLogic(String userName, String email, String mobileNum, String password);
}

class DriverRegister implements Iregister {

    @Override
    public User registerLogic(String userName, String email, String mobileNum, String password) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter nationalId: ");
        String nationalId = input.next();
        System.out.println("Enter licenseId:");
        String licenseId = input.next();
        Driver driver=new Driver();
        driver.setEmail(email);
        driver.setMobileNum(mobileNum);
        driver.setPassword(password);
        driver.setUserName(userName);
        driver.setDrivingLicense(licenseId);
        driver.setNationalId(nationalId);
        driver.setStatus("pandding");
        return driver;
    }
}

class PassengerRegister implements Iregister {

    @Override
    public User registerLogic(String userName, String email, String mobileNum, String password) {
        Scanner input = new Scanner(System.in);
        Passenger passenger=new Passenger();
        passenger.setEmail(email);
        passenger.setMobileNum(mobileNum);
        passenger.setPassword(password);
        passenger.setUserName(userName);
        passenger.setStatus("Active");
        System.out.println("Enter a birthday by following syntax(monthnumber,daynumber)");
        String birthday=input.next();
        passenger.setBirthday(birthday);
        return passenger;
    }
}
