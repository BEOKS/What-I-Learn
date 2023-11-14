package com.example.validation2;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SampleController {
    @GetMapping
    public ResponseEntity<Item> get(@Validated @ModelAttribute Item item){
        return ResponseEntity.ok().body(item);
    }
}
