package com.example.Phase2.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Admin extends User {

        public Admin(){}
        public  Admin(@JsonProperty("userName") String userName,
                      @JsonProperty("email") String  email,
                      @JsonProperty("mobileNum") String  mobileNum,
                      @JsonProperty("password") String password) {

                this.setUserName(userName);
                this.setEmail(email);
                this.setMobileNum(mobileNum);
                this.setPassword(password);


        }
        private ArrayList<String> discountArea=new ArrayList<>();
        public ArrayList<String>getDicountArea(){return discountArea;}
        public void setDiscountArea(String newArea){discountArea.add(newArea);}
}



/*
import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin extends User {
public  Admin(@JsonProperty("userName") String userName,
            @JsonProperty("email") String  email,
            @JsonProperty("mobileNum") String  mobileNum,
            @JsonProperty("password") String password) {

        this.setUserName(userName);
        this.setEmail(email);
        this.setMobileNum(mobileNum);
        this.setPassword(password);


}

}
*/