package com.example.validation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.validation.Validator
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class ValidationApplication: WebMvcConfigurer

fun main(args: Array<String>) {
    runApplication<ValidationApplication>(*args)
}


@Configuration
class GlobalValidatorConfiguration : WebMvcConfigurer{
    override fun getValidator(): Validator? {
        return Item.Test.ItemValidator()
    }
}