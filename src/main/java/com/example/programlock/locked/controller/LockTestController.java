package com.example.programlock.locked.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/doTest")
public class LockTestController {


    @GetMapping("/test")
    @ResponseBody
    public void doTest(HttpServletRequest request){
        System.out.println("=======");

    }


}
