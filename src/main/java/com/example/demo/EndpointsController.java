package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class EndpointsController {

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
