package com.example.demo;

public class MathService {

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
