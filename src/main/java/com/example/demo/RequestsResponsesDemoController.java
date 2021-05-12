package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class RequestsResponsesDemoController {
    @Autowired
   MathService mathService;

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/math/pi")
    public String mathPi() {
        return "3.141592653589793";

    }
    @GetMapping("/math/calculate")
    public String calculate(@RequestParam(defaultValue = "null") String operation, @RequestParam Integer x, @RequestParam Integer y) {
        if (operation.equals("add")) {
            //int result=Integer.parseInt(x)+Integer.parseInt(y);
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

    @PostMapping("/math/sum")
    public String calculateSumOfNumbers(@RequestParam List<Integer> n) {
        Integer sum = 0;
        StringBuilder str = new StringBuilder();
        //List values = null;
        for (Integer num : n) {
            sum = sum + num;
            str.append(num + "+");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString() + "=" + String.valueOf(sum);
    }
    @GetMapping("/math/calculateuseservice")
    public String calculateUsingMathService(@RequestParam(defaultValue = "null") String operation, @RequestParam Integer x, @RequestParam Integer y) {
        return mathService.calculate(operation, x, y);
    }
}
