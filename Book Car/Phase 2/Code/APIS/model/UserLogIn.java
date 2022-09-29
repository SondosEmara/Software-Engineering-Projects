package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLogIn {

    private String userName;
    private String password;
    UserLogIn(@JsonProperty("userName") String userName,
                        @JsonProperty("password") String password){

        this.password=password;
        this.userName=userName;
    }
    public String getUserName(){return userName ;}
    public String getPassword(){return password;}

}
