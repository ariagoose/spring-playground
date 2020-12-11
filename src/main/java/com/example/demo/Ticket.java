package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
    @JsonProperty("Passenger")
    private Person passenger;

    @JsonProperty("Price")
    private int cost;

    public Person getPassenger() {
        return passenger;
    }

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }




}
