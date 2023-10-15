package com.example.sentry

import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExceptionProducer {
    val log:Logger=LoggerFactory.getLogger(javaClass)
    @GetMapping
    fun produce(){
        log.info("produce exception")
        throw Exception("ExceptionProducer produce exception")
    }
}