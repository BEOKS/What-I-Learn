package com.example.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
    @GetMapping("/")
    public String login(@Login Member member){

    }
}
