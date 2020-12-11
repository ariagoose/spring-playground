package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
public class EndpointsController {

    //@PostMapping("/flights/tickets/total")

    @GetMapping("/flights/flight")
    public Flight getFlight(){
        Flight f = new Flight();
        f.setDeparts(new Date(2017 - 1900, Calendar.APRIL, 21, 14+1, 34));
        f.setTicket();
        return f;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights(){
        Flight f1 = new Flight();
        f1.setDeparts(new Date(2017-1900, Calendar.APRIL, 21, 14+1, 34));
        f1.setTicket();
        f1.getTickets().get(0).getPassenger().setFirstName("Tom");
        f1.getTickets().get(0).getPassenger().setLastName("Smith");



        Flight f2 = new Flight();
        f2.setDeparts(new Date(2017-1900, Calendar.APRIL, 21, 14+1, 34));
        f2.setTicket();
        f2.getTickets().get(0).getPassenger().setFirstName("Sara");
        f2.getTickets().get(0).getPassenger().setLastName("Smith");


        return Arrays.asList(f1, f2);
    }

    @GetMapping("/math/pi")
    public String getPi(){
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public String getCalc(@RequestParam(value = "operation", defaultValue = "add") String operation, @RequestParam int x, @RequestParam int y){
        return MathService.calculate(x, y, operation);
    }

    @PostMapping("/math/sum")
    public String postSum(@RequestParam Integer [] n){

        return MathService.sum(n);
    }

    @GetMapping("/")
    public String getIndex(){
        return "GET to index route";
    }

    @GetMapping("/tasks")
    public String getTasks() {
        return "These are tasks";
    }

    @PostMapping("/tasks")
    public String createTask() {
        return "You just POSTed to /tasks";
    }

    @RequestMapping("/math/volume/{l}/{w}/{h}")
    public String requestVolume(@PathVariable int l, @PathVariable int w, @PathVariable int h ){
        int volume = l * w * h;
        return String.format("The volume of a %dx%dx%d rectangle is %d", l, w, h, volume);
    }

    @PostMapping(value = "/math/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getArea(@RequestParam Map<String, String> formData){
        return MathService.getArea(formData);
    }


 }
