package com.qd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @RequestMapping("/sayHi")
    public String sayHi(){
        return "你好 Springboot";
    }
}
