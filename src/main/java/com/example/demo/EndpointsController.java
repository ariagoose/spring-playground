package com.example.demo;

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
    public String postSum(@RequestParam Integer [] n){

        Integer sum = 0;
        String result = "";

        for (int i = 0; i<n.length; i++){
            sum += n[i];
            if (i<n.length -1) {
                result = result.concat(n[i] + " + ");
            }else{
                result = result.concat(n[i] + " = ");
            }
        }
        result = result.concat(String.valueOf(sum));
        return result;
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
