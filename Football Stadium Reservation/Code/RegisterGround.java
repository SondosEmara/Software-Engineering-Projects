package gofootball;

/**
 * RegisterGround class store all the information about new PlayGround.
 *
 * @author Mahmoud_Shaltout
 */
public class RegisterGround {

    private String groundName;
    private String place;
    private String size;
    private int price;
    private String rentalTime;
    private String duration;

    /**
     * Constructor that will call when make a new object of class and takes 7
     * Parameters
     *
     * @param groundName it takes the name of the playground and store it in
     * private attribute(groundName)
     * @param place it takes the location of the playground and store it in
     * private attribute(place)
     * @param size it takes the size of the playground and store in private
     * attribute(size)
     * @param price it takes the price of the playground and sore it in private
     * attribute(price)
     * @param rentalTime it takes the rental time of the playground and sore it
     * in private attribute(rentalTime)
     * @param duration it takes the duration of the playground and store it in
     * private attribute(duration)
     *
     */
    //constructor
    public RegisterGround(String groundName, String place, String size, int price, String rentalTime, String duration) {
        this.groundName = groundName;
        this.place = place;
        this.size = size;
        this.price = price;
        this.rentalTime = rentalTime;
        this.duration = duration;
    }

    RegisterGround() {
    }

    /**
     * setGroundName is a void function that take a String groundName as a
     * parameter and store it
     *
     * @param groundName that refer to the name of the playground
     */
    //setters
    public void setGroundName(String groundName) {
        this.groundName = groundName;
    }

    /**
     * setPlace is a void function that take a String place as a parameter and
     * store it
     *
     * @param place that refer to the place of the playground.
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * setSize is a void function that take a size as a parameter and store it
     *
     * @param size that refer to the size of the playground
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * setPrice is a void function that take a int price as a parameter and
     * store it
     *
     * @param price that refer to the price of the playground
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * setRentalTime is a void function that take a String rentalTime as a
     * parameter and store it
     *
     * @param rentalTime that refer to the rental time of the playground
     */
    public void setRentalTime(String rentalTime) {
        this.rentalTime = rentalTime;
    }

    /**
     * setDuration is a void function that take a String duration as a parameter
     * and store it
     *
     * @param duration that refer to the duration of the playground
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * getGroundName is a return function that
     *
     * @return the name of the playground as a String
     */
    public String getGroundName() {
        return groundName;
    }

    /**
     * getPlace is a return function that
     *
     * @return the place of the playground as a String
     */
    public String getPlace() {
        return place;
    }

    /**
     * getSize is a return function that
     *
     * @return the size of the playground as a String
     */
    public String getSize() {
        return size;
    }

    /**
     * getPrice is a return function that
     *
     * @return the price of the playground as an int
     */
    public int getPrice() {
        return price;
    }

    /**
     * getRentalTime is a return function that
     *
     * @return the rental time of the playground as a String
     */
    public String getRentalTime() {
        return rentalTime;
    }

    /**
     * getDuration is a return function that
     *
     * @return the duration of the playground as a String
     */
    public String getDuration() {
        return duration;
    }
}
