package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class MathService {
    public String calculate(String operation,Integer x,Integer y){
        Integer result;
        String operator;
        if (operation.equals("add")) {
             result = x + y;
            operator="+";
        } else if (operation.equals("multiply")) {
             result = x * y;
            operator="x";
        } else if (operation.equals("subtract")) {
             result = x - y;
            operator="-";
        } else if (operation.equals("divide")) {
             result = x / y;
            operator="/";
        } else {
             result = x + y;
             operator="+";
        }
        return String.format("%d %s %d = %d",x,operator,y,result);

    }
//String.format("%d + %d = %d",y,z,result);

}
