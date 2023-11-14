package com.example.validation2;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
public class NotFutureV implements ConstraintValidator<NotFuture, YearMonth> {
    @Override
    public boolean isValid(YearMonth value, ConstraintValidatorContext context) {
        System.out.println("NotFutureV value = " + value);
        if(value==null){
            return false;
        }
        return !value.isAfter(YearMonth.now());
    }
}
