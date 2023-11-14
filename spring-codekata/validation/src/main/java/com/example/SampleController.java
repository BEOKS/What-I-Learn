package com.example;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;

@RestController
public class SampleController {
    @GetMapping
    String get(@Valid @NotNull @NotBlank String yearMonth){
        return yearMonth;
    }
}
