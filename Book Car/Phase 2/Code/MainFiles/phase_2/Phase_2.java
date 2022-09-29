
package phase_2;

import java.util.Scanner;

public class Phase_2 {

    public static void main(String[] args)  {
 
        User userAdmin=new Admin();
        userAdmin.setEmail("admin");
        userAdmin.setMobileNum("admin");
        userAdmin.setPassword("admin");
        userAdmin.setStatus("admin");
        userAdmin.setUserName("admin");
        UserManager.addNewAdmin(userAdmin);
        
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (choice != 3) {

            System.out.println("1-LogIn\n" + "2-Register\n" + "3-exit\n"); 
            choice = input.nextInt();
            switch (choice) {

                case 1:

                    System.out.println("Enter UserName:");
                    String userName = input.next();
                    System.out.println("Enter password:");
                    String password = input.next();

                    try{
                        User user =UserManager.searchForlogIn(userName, password);
                       
                        user.openScreen();
                    }catch (Exception error){
                        System.out.println(error.getMessage());
                    } 
                    break;

                    
                case 2:
                    int choice2 = 0;
                        while (choice2 != 3) {
                        System.out.println("1-UserRegister\n" + "2-DriverRegister\n" + "3-backTOLogIn\n");
                        choice2= input.nextInt();
                        switch (choice2) {
                           
                            case 1:
                                Iregister passengerUser=new PassengerRegister();
                                UserManager.addNewUser(passengerUser);
                                break;
                                
                            case 2:
                                Iregister driverRegister=new DriverRegister();
                                UserManager.addNewUser(driverRegister);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Please Enter the correct choice");
                                break;
                        }
                    }
                    break;
                    
                case 3:
                    System.out.println("Thanks to use our Program");
                    break;
                default:
                    System.out.println("please enter the correct choice ,Try Again");
                    break;
            }

        }
        
      
    }

}
