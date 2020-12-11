package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight {

    @JsonProperty("Departs")
    private Date departs;
    @JsonProperty("Ticket")
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public void setTicket() {
        Ticket ticket1 = new Ticket();
        ticket1.setPassenger(getPerson());
        ticket1.setCost(200);

        tickets.add(ticket1);
    }

    public List<Ticket> getTickets(){
        return tickets;
    }

    private Person getPerson(){
        Person person1 = new Person();
        person1.setFirstName("some name");
        person1.setLastName("some other name");
        return person1;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "EST")
    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date dateTime) {
        this.departs = dateTime;
    }
}
