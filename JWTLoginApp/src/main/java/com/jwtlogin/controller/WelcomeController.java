package com.jwtlogin.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome Pluto bhai! You're authenticated successfully 🚀";
    }
}
