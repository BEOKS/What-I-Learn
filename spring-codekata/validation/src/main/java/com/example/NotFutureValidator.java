package com.example;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.YearMonth;

public class NotFutureValidator implements ConstraintValidator<NotFuture, YearMonth> {
    @Override
    public boolean isValid(YearMonth value, ConstraintValidatorContext context) {
        return !value.isAfter(YearMonth.now());
    }
}
