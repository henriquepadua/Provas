package com.digitalinnovationone.springboot.Controller;

import org.springboot.web.bind.annotation.RestController;

@RestController
public class HelloController{

    @GetMapping("/")
    public String helloMessage(){
        return "Hello, Senhoras e Senhores";
    }
}