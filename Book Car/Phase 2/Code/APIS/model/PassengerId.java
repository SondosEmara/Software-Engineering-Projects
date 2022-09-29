package com.example.Phase2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PassengerId {

        private int passengerId;
        PassengerId(@JsonProperty("passengerId") int passengerId){
            this.passengerId=passengerId;
        }
        public int getPassengerId(){return passengerId;}


}
