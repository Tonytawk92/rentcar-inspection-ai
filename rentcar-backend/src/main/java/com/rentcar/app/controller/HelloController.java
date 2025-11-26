package com.rentcar.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple REST controller for testing purposes.
 * This controller provides a basic endpoint to check if the application is running.
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    /**
     * A simple GET endpoint that returns a "Hello, World!" string.
     * This can be used to quickly test if the API is responsive.
     * @return The string "Hello, World!".
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
