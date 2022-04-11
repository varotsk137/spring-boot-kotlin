package com.example.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class KotlinApplication

fun main(args: Array<String>) {
	runApplication<KotlinApplication>(*args)
}
