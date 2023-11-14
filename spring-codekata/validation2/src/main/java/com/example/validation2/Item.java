package com.example.validation2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Year;
import java.time.YearMonth;

public record Item(
        @NotFuture YearMonth yearMonth
        ){
}
