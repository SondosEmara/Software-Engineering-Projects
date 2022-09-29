package gofootball;

import java.util.ArrayList;

/**
 * This class includes the page of the playground that contain all information
 * like user information, playground information
 *
 * @author Sondos Emara Gomaa and Mahmoud_Mohamed_Abd-Elazeem
 */
public class AllInformation {

    private ArrayList<Account> users;
    private ArrayList<RegisterGround> playGround;
    private ArrayList<Account> userhaveGround;

    /**
     * Constructor that will call when make a new object of class and takes no
     * Parameters ,it calls 3 lists (ArrayList Account), ArrayList
     * RegisterGround, and ArrayList Account
     */
    public AllInformation() {
        users = new ArrayList<Account>();
        playGround = new ArrayList<RegisterGround>();
        userhaveGround = new ArrayList<Account>();
    }

    /**
     * addnewAccount is a void function that take an object from Account class
     * as a parameter and store it
     *
     * @param newUser is an object to call the constructor of Account class and
     * add new one
     */
    public void addnewAccount(Account newUser) {
        users.add(newUser);
    }

    /**
     * addnewPlayGround is a void function that take 2 objects from
     * RegisterGround class and Account class as a parameters and store them
     *
     * @param newPlayGround is an object to call the constructor of
     * RegisterGround class and add new one
     * @param account is an object to call the constructor of Account class and
     * add new one
     */
    public void addnewPlayGround(RegisterGround newPlayGround, Account account) {

        playGround.add(newPlayGround);
        userhaveGround.add(account);

    }

    /**
     * searchAccount is a function from type Account that takes int index and
     * String passWord as a parameters and check them
     *
     * @param index is a parameter that contain the user index and check if
     * exist or not
     * @param passWord is a parameter that contain the user password and check
     * if correct or not
     * @return the user information if it is valid
     * @throws Exception that return message if any user information is not
     * valid or not correct
     */
    public Account searchAccount(int index, String passWord) throws Exception {

        Account user = users.get(index);
        if (user == null) {
            throw new Exception("This Account not exist ,try again");
        } else if (!user.getuserInfo().getpassword().equals(passWord)) {
            throw new Exception("the password not correct,Try Again");
        }
        return user;
    }

    /**
     * searchPlayGround is a function from type RegisterGround that takes int
     * index as a parameters and check it
     *
     * @param index is a parameter that contain the playground index and check
     * if exist or not
     * @return the playground information if it is valid
     * @throws Exception that return message if any playground information is
     * not valid or not correct
     */
    public RegisterGround searchPlayGround(int index) throws Exception {
        RegisterGround playgroundInfo = playGround.get(index);
        if (playgroundInfo == null) {
            throw new Exception("Not exist the choice PlayGround,Try Again");
        } else {
            return playgroundInfo;
        }

    }

    /**
     * userhaveGround is a function from type Account that takes int index as a
     * parameter and store it
     *
     * @param index is a parameter that contain the index of the user's
     * playground
     * @return the if user have account or not
     */
    public Account userhaveGround(int index) {

        return userhaveGround.get(index);

    }

    /**
     * showallGround is a void function that show all playgrounds information
     */
    public void showallGround() {

        int counter = 1;
        for (RegisterGround It : playGround) {
            System.out.println(counter + "-" + " GroundName: " + It.getGroundName() + " GroundId: " + counter + " Place: " + It.getPlace() + " Price: " + It.getPrice() + " RentalTime: " + It.getRentalTime() + " Duration: " + It.getDuration());
            counter++;
        }

    }

    /**
     * editAccount is a void function that takes accountEdit from type Account
     * and int index and as a parameters and store them to edit the account
     *
     * @param accountEdit is an object to call the constructor of Account class
     * @param index is a parameter that contain the account index and to edit on
     * it
     */
    public void editAccount(Account accountEdit, int index) {

        users.set(index, accountEdit);

    }

}
