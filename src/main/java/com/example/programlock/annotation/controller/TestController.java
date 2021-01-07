package com.example.programlock.annotation.controller;

import com.example.programlock.annotation.config.CherryAnnotation;
import com.example.programlock.annotation.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/annotation")
public class TestController {

    @Autowired
    private Student student;

    @GetMapping("/sss")
    @CherryAnnotation(name = "11",age = 23,score = {99,66,77})
    public void testScanner(){
        System.out.println("==========方法中");

    }

    @GetMapping("/errrf")
    @ResponseBody
    public String ggff(int f){
        System.out.println("==========方法中");
        student.study(1);
        return "11";

    }

}
