package com.example.demo

import org.springframework.stereotype.Service


@Service
class HelloService {

    fun hello(): String {
        println("hello")
        println("hello")
        return "Hello World!"
    }
}