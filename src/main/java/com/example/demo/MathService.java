package com.example.demo;

import java.util.Map;

public class MathService {

    public static String getArea(Map<String, String> formData){
        String answer ="";

        if (formData.get("type").equals("circle") && (formData.get("radius") != null)){
            int rad = Integer.parseInt(formData.get("radius"));
            double area = (rad^2) * Math.PI;
            area = Math.round(area);
            answer = "The area of a circle with a radius of " + rad + " is " + area;
        }else if (formData.get("type").equals("rectangle") && (formData.get("width") != null) && (formData.get("height") != null)){
            int width = Integer.parseInt(formData.get("width"));
            int height = Integer.parseInt(formData.get("height"));
            int area = width * height;
            answer = "The area of a " + width + "x" + height + " rectangle is " + area;
        }else{
            answer = "Invalid";
        }

        return answer;
    }

    public static String calculate (int x, int y, String operand){
        int answer = 0;
        String result = "";

        if (operand.equals("divide")){
            operand = " / ";
            answer = x/y;
        }else if (operand.equals("subtract")){
            operand = " - ";
            answer = x-y;
        }else if (operand.equals("multiply")){
            operand = " * ";
            answer = x*y;
        }else {
            operand = " + ";
            answer = x+y;
        }

        result = x + operand + y + " = " + answer;

        return result;
    }

    public static String sum(Integer[] n){
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
}
