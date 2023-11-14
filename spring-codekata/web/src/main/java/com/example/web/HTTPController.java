package com.example.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/**")
public class HTTPController {
    @GetMapping
    void get(HttpServletRequest request){
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String protocol = request.getProtocol();
        System.out.println("method = " + method);
        System.out.println("requestURI = " + requestURI);
        System.out.println("protocol = " + protocol);
    }
}
