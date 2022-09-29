package gofootball;

/**
 * this class represents all account information and have attributes like
 * userInfo, playGround, userWallet, accountid, groundid
 *
 * @author Nada omar fathi
 */
public class Account {

    /**
     * class private attributes
     */
    private Authentication userInfo;
    private RegisterGround playGround;
    private Wallet userWallet;
    private int accountid;
    private int groundid;

    /**
     * Constructor that will call when make a new object of class and takes no
     * Parameters ,and it will create new objects from Authentication class ,
     * RegisterGround class, and Wallet class
     */
    public Account() {
        userInfo = new Authentication();
        playGround = new RegisterGround();
        userWallet = new Wallet();
    }

    //getters
    /**
     * getuserInfo is a return function that
     *
     * @return the user information to Authentication class
     */
    public Authentication getuserInfo() {
        return userInfo;
    }

    /**
     * getuserWallet is a return function that
     *
     * @return the user wallet information
     */
    public Wallet getuserWallet() {
        return userWallet;
    }

    /**
     * getaccountid is a return function that
     *
     * @return the account id as an int value
     */
    public int getaccountid() {
        return accountid;
    }

    /**
     * getgroundid is a return function that
     *
     * @return the ground id as an int value
     */
    public int getgroundid() {
        return groundid;
    }

    //setters
    /**
     * setuserInfo is void function that takes parameter of type Authentication
     * and store it
     *
     * @param userInfo that refer to the user information
     */
    public void setuserInfo(Authentication userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * setuserWallet is void function that takes parameter of type Wallet and
     * store it
     *
     * @param userWallet that refer to the user Wallet
     */
    public void setuserWallet(Wallet userWallet) {
        this.userWallet = userWallet;
    }

    /**
     * setaccountid is void function that takes int accountid as parameter and
     * store it
     *
     * @param accountid that refer to the account id
     */
    public void setaccountid(int accountid) {
        this.accountid = accountid;
    }

    /**
     * setgroundid is void function that takes int groundid as parameter and
     * store it
     *
     * @param groundid that refer to the ground id
     */
    public void setgroundid(int groundid) {
        this.groundid = groundid;
    }

    /**
     * setplayGround is void function that takes parameter of type
     * RegisterGround and store it
     *
     * @param playGround that refer to the playGround
     */
    public void setplayGround(RegisterGround playGround) {
        this.playGround = playGround;
    }
}
