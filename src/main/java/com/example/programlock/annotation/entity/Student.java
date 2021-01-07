package com.example.programlock.annotation.entity;

import com.example.programlock.annotation.config.CherryAnnotation;
import org.springframework.stereotype.Component;

@Component
public class Student {
    public static final String aa="4";
    public static String age="";

    @CherryAnnotation(name = "2",age = 23,score = {99,66,77})
    public String study(int times){
        for(int i = 0; i < times; i++){
            System.out.println("Good Good Study, Day Day Up!");
        }
        System.out.println("===========age:"+age);
        return "hello";
    }
}
