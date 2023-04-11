package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val service: HelloService,
) {

    @GetMapping("/")
    fun hello(): String {
        return service.hello()
    }
}