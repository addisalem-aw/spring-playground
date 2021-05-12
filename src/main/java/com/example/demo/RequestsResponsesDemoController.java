package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class RequestsResponsesDemoController {
    @Autowired
   MathService mathService;

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

   /////////////////////////////// Spring Math: PI with GET
    @GetMapping("/math/pi")
    public String mathPi() {
        return "3.141592653589793";

    }
    ///////////////////////////////Spring Math:Calculate

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
//////////////////////////////////////////Spring Math:Volume with Path Variables
    @GetMapping("/math/volume/{length}/{width}/{height}")
    public String getVolumeUsingPathVariable(@PathVariable Integer length,@PathVariable Integer width,@PathVariable Integer height){
        Integer volume=length*width*height;
        return "The volume of a "+String.valueOf(length)+"x"
                +String.valueOf(width)+"x"+String.valueOf(height)
                +" rectangle is "+String.valueOf(volume);
    }

    @PostMapping("/math/volume/{length}/{width}/{height}")
    public String postVolumeUsingPathVariable(@PathVariable Integer length,@PathVariable Integer width,@PathVariable Integer height){
        Integer volume=length*width*height;
        return "The volume of a "+String.valueOf(length)+"x"
                +String.valueOf(width)+"x"+String.valueOf(height)
                +" rectangle is "+String.valueOf(volume);
    }

    @PatchMapping("/math/volume/{length}/{width}/{height}")
    public String patchVolumeUsingPathVariable(@PathVariable Integer length,@PathVariable Integer width,@PathVariable Integer height){
        Integer volume=length*width*height;
        return "The volume of a "+String.valueOf(length)+"x"
                +String.valueOf(width)+"x"+String.valueOf(height)
                +" rectangle is "+String.valueOf(volume);
    }




}
