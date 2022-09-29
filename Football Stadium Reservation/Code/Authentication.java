package gofootball;

/**
 * This class includes the page of the Authentication and have attributes like
 * userName, password, mobilePhone, and email
 *
 * @author Sondos Emara Gomaa
 */
public class Authentication {

    private String userName;
    private String password;
    private String mobilePhone;
    private String email;

    /**
     * Constructor that will call when make a new object of class and takes 4
     * Parameters
     *
     * @param userName it takes the name of the user and sore it in private
     * attribute(userName)
     * @param password it takes the password of the user and sore it in private
     * attribute(password)
     * @param email it takes the email of the user and sore it in private
     * attribute(email)
     * @param mobilePhone it takes the mobilePhone number of the user and sore
     * it in private attribute(mobilePhone)
     */
    public Authentication(String userName, String password, String email, String mobilePhone) {
        this.userName = userName;
        this.password = password;
        this.mobilePhone = mobilePhone;
        this.email = email;
    }

    Authentication() {
    }

    /**
     * validInformation is a void function that check the validation of user
     * information
     *
     * @throws Exception that return message if any user information is not
     * valid or not correct
     */
    public void validInformation() throws Exception {

        if (((mobilePhone.length() < 10)) || (mobilePhone.length() > 10) || (!(email.contains("@gmail.com")))) {
            throw new Exception("The information not correct ,try again");
        }

    }

    /**
     * setuserName is a void function that take a String userName as a parameter
     * and store it
     *
     * @param userName that refer to the name of the user
     */
    public void setuserName(String userName) {

        this.userName = userName;
    }

    /**
     * setpassword is a void function that take a String password as a parameter
     * and store it
     *
     * @param password that refer to the password of the user
     */
    public void setpassword(String password) {
        this.password = password;
    }

    /**
     * setmobilePhone is a void function that take a String mobilePhone as a
     * parameter and store it
     *
     * @param mobilePhone that refer to the mobile phone number of the user
     */
    public void setmobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * setemail is a void function that take a String email as a parameter and
     * store it
     *
     * @param email that refer to the email of the user
     */
    public void setemail(String email) {
        this.email = email;
    }

    /**
     * getuserName is a return function that
     *
     * @return the name of the user as a String
     */
    public String getuserName() {
        return userName;
    }

    /**
     * getpassword is a return function that
     *
     * @return the name of the user as a String
     */
    public String getpassword() {
        return password;
    }

    /**
     * getmobilePhone is a return function that
     *
     * @return the mobile phone number of the user as a String
     */
    public String getmobilePhone() {
        return mobilePhone;
    }

    /**
     * getemail is a return function that
     *
     * @return the email of the user as a String
     */
    public String getemail() {
        return email;
    }

}
