package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogOut {

    private int UserId;

    public LogOut(@JsonProperty("userid") int userId) {
        UserId = userId;
    }

    public int getUserId() {
        return UserId;
    }

}
