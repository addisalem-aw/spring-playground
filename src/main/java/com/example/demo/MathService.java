package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class MathService {
    public String calculate(String operation,Integer x,Integer y){
        if (operation.equals("add")) {
            Integer result = x + y;
            return String.valueOf(x) + "+" + String.valueOf(y) + "=" + String.valueOf(result);
        } else if (operation.equals("multiply")) {
            Integer result = x * y;
            return String.valueOf(x) + "*" + String.valueOf(y) + "=" + String.valueOf(result);
        } else if (operation.equals("subtract")) {
            Integer result = x - y;
            return String.valueOf(x) + "-" + String.valueOf(y) + "=" + String.valueOf(result);
        } else if (operation.equals("divide")) {
            Integer result = x / y;
            return String.valueOf(x) + "/" + String.valueOf(y) + "=" + String.valueOf(result);
        } else {
            Integer result = x + y;
            return String.valueOf(x) + "+" + String.valueOf(y) + "=" + String.valueOf(result);
        }
    }

}
