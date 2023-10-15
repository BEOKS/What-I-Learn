package com.example.sentry

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SentryApplication

fun main(args: Array<String>) {
	runApplication<SentryApplication>(*args)
}
