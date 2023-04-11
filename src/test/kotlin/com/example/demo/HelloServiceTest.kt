package com.example.demo

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class HelloServiceTest {

    @Test
    fun hello() {
        val service = HelloService()
        assertEquals("Hello World!", service.hello())
    }
}