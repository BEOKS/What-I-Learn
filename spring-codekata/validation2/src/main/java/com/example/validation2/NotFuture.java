package com.example.validation2;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotFutureV.class )
public @interface NotFuture {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "미래 값을 넣어서는 안됩니다.";

}
