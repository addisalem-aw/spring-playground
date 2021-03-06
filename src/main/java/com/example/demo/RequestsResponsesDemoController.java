package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class RequestsResponsesDemoController {
    @Autowired
   MathService mathService;
    @Autowired
    ShapeService shapeService;
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

    @PostMapping("/math/sum")
    public String calculateSumOfNumbers(@RequestParam List<Integer> n) {
        Integer sum = 0;
        StringBuilder str = new StringBuilder();
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
        return "The volume of a "+length+"x"
                +width+"x"+height
                +" rectangle is "+volume;
    }
    @PostMapping("/math/volume/{length}/{width}/{height}")
    public String postVolumeUsingPathVariable(@PathVariable Integer length,@PathVariable Integer width,@PathVariable Integer height){
        Integer volume=length*width*height;
        return "The volume of a "+length+"x"
                +width+"x"+height
                +" rectangle is "+volume;
    }

    @PatchMapping("/math/volume/{length}/{width}/{height}")
    public String patchVolumeUsingPathVariable(@PathVariable Integer length,@PathVariable Integer width,@PathVariable Integer height){
        Integer volume=length*width*height;
        return "The volume of a "+length+"x"
                +width+"x"+height
                +" rectangle is "+volume;
    }

    ////////////////Spring Math: Calculate area
    @PostMapping(value = "/math/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postAreaUsingFormData( Shape shape){
        return shapeService.calculateArea(shape);
    }

}
