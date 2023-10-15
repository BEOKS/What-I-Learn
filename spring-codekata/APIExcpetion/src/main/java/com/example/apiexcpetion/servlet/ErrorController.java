package com.example.apiexcpetion.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ErrorController {
    @RequestMapping(value = "/error-page/500",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> errorPage500api(
            HttpServletRequest request, HttpServletResponse response
    ){
        HashMap<String, Object> result = new HashMap<>();
        Exception attribute = (Exception) request.getAttribute("javax.servlet.error.exception");
        System.out.println("attribute = " + attribute);
        return ResponseEntity.badRequest().body(result);
    }
}
