package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class ShapeService {
    public String calculateArea(Shape shape) {

        if(shape.getType().equals("circle")){
            Double area=Math.PI*shape.getRadius()*shape.getRadius();
            return "Area of a circle with a radius of "+shape.getRadius()+ " is "+ area;
        }
        else if(shape.getType().equals("rectangle")){
            Double area=shape.getWidth()*shape.getHeight();
            return "Area of a rectangle with a width of "+shape.getWidth()+" and height of "+shape.getHeight() + " is "+ area;

        }
        else
            return "Invalid";
    }
}
