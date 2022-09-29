package gofootball;

import java.util.Scanner;

/**
 * goFootball is main of the program that call the other classes.
 *
 * @author Sondos Emara Gomaa
 */
public class GoFootball {

    public static void main(String[] args) {

        int choiceNum1 = 0, accountId = 1, choiceNum2 = 0, price, groundId = 1, despoistMoney, choice3, userId;
        String userName, password, phone, email, groundName, place, size, rentalTime, duration;
        Scanner Input = new Scanner(System.in);
        Authentication newUser;
        Account userAccount;
        AllInformation systemInformation = new AllInformation();

        while (choiceNum1 != 3) {

            System.out.println("1-log in");
            System.out.println("2-Create new account");
            System.out.println("3-Exist");

            choiceNum1 = Input.nextInt();

            switch (choiceNum1) {
                case 1: {

                    System.out.println("Enter your accountId");
                    userId = Input.nextInt();

                    System.out.println("Enter your passWord");
                    password = Input.next();

                    //check if the user is exist in the systemInformation or not.
                    try {
                        if (userId >= accountId || userId == 0) {
                            throw new Exception("The Account Id not Correct");
                        }
                        userAccount = systemInformation.searchAccount(userId - 1, password);
                    } catch (Exception error) {
                        System.out.println(error.getMessage());
                        continue;
                    }
                    System.out.println("Success Log in");
                    choiceNum2 = 0;
                    //after the log in your account will show the features of the account.
                    while (choiceNum2 != 5) {

                        System.out.println("choose from your feature account");
                        System.out.println("1.Booking");
                        System.out.println("2.Register Playground");
                        System.out.println("3.wallet deposit");
                        System.out.println("4.show wallet currentBalance");
                        System.out.println("5.log out");

                        choiceNum2 = Input.nextInt();

                        switch (choiceNum2) {

                            case 1: {
                                int rentalMoney;
                                RegisterGround PlayGround;
                                systemInformation.showallGround();

                                System.out.println("choose the GroundId");
                                choice3 = Input.nextInt();

                                try {
                                    if (choice3 >= groundId || choice3 == 0) {
                                        throw new Exception("The groundId not Correct");
                                    }
                                    PlayGround = systemInformation.searchPlayGround(choice3 - 1);
                                } catch (Exception error) {
                                    System.out.println(error.getMessage());
                                    continue;
                                }

                                //Withdraw from the user who book playground.
                                rentalMoney = PlayGround.getPrice();
                                try {
                                    userAccount.getuserWallet().withdraw(rentalMoney);
                                } catch (Exception error) {
                                    System.out.println(error.getMessage());
                                    continue;
                                }
                                //update the account in the all SystemInformation.
                                systemInformation.editAccount(userAccount, userId - 1);

                                //add the wallet playgroundowner.
                                Account plagroundOwner;
                                plagroundOwner = systemInformation.userhaveGround(choice3 - 1);
                                (plagroundOwner.getuserWallet()).deposit(rentalMoney);

                                //update the account of the playgroundowner in the all SystemInformation.
                                systemInformation.editAccount(plagroundOwner, plagroundOwner.getaccountid() - 1);

                                System.out.println("Success Booking PlayGround");
                                break;

                            }
                            case 2: {
                                System.out.println("Enter groundName");
                                groundName = Input.next();
                                System.out.println("Enter place ground ");
                                place = Input.next();
                                System.out.println("Enter size");
                                size = Input.next();
                                System.out.println("Enter price");
                                price = Input.nextInt();
                                System.out.println("Enter rentalTime");
                                rentalTime = Input.next();
                                System.out.println("Enter duration");
                                duration = Input.next();

                                RegisterGround groundinfo = new RegisterGround(groundName, place, size, price, rentalTime, duration);
                                userAccount.setplayGround(groundinfo);
                                userAccount.setgroundid(groundId);
                                systemInformation.addnewPlayGround(groundinfo, userAccount);

                                System.out.println("Success Register playground");
                                ++groundId;
                                break;
                            }

                            case 3: {
                                System.out.println("Enter the despoistMoney");
                                despoistMoney = Input.nextInt();
                                (userAccount.getuserWallet()).deposit(despoistMoney);
                                systemInformation.editAccount(userAccount, userId - 1);
                                System.out.println("Success despoistMoney");
                                break;

                            }
                            case 4: {
                                System.out.println("your currentBalance is");
                                System.out.println((userAccount.getuserWallet()).getcurrentBalance());
                                break;
                            }
                            case 5: {
                                System.out.println("Success Log Out");
                                break;
                            }
                            default: {
                                System.out.println("please enter correct choosenumber");
                                break;
                            }
                        }

                    }

                    break;

                }

                case 2: {

                    System.out.println("Enter Your userName");
                    userName = Input.next();

                    System.out.println("Enter Your password");
                    password = Input.next();

                    System.out.println("Enter Your email ");
                    email = Input.next();

                    System.out.println("Enter Your phone ");
                    phone = Input.next();

                    //from class Authentication
                    try {

                        newUser = new Authentication(userName, password, email, phone);
                        newUser.validInformation();
                    } catch (Exception error) {
                        System.out.println(error.getMessage());
                        continue;
                    }

                    //from class account
                    userAccount = new Account();
                    userAccount.setuserInfo(newUser);
                    userAccount.setaccountid(accountId);

                    //from class allInformation.
                    systemInformation.addnewAccount(userAccount);

                    //increament to the next create account id 
                    System.out.println("Success create new account ,your accountId is" + ' ' + accountId);
                    accountId++;
                    break;
                }
                case 3: {
                    System.out.println("Thank you to choose our program");
                    break;
                }
                default: {
                    System.out.println("please enter the correct choice number");
                    break;
                }

            }

        }

    }

}
