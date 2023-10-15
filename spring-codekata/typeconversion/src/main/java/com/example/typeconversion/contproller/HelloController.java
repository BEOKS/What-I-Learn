package com.example.typeconversion.contproller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final List<TestIn> sample;
    @GetMapping("/hello-v1")
    public String helloV1(HttpServletResponse response){
        System.out.println("test2 = " + sample);
        return "";
    }
}
