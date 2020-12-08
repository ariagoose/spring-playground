package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointsController {

    @GetMapping("/math/pi")
    public String getPi(){
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public String getCalc(@RequestParam(value = "operation", defaultValue = "add") String operation, @RequestParam int x, @RequestParam int y){
        String result;
        int answer;
        String operand;

        if (operation.equals("divide")){
            operand = " / ";
            answer = x/y;
        }else if (operation.equals("subtract")){
            operand = " - ";
            answer = x-y;
        }else if (operation.equals("multiply")){
            operand = " * ";
            answer = x*y;
        }else {
            operand = " + ";
            answer = x+y;
        }

        result = x + operand + y + " = " + answer;
        return result;
    }

    @PostMapping("/math/sum")
    public String postSum(){
        return "";
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

}
