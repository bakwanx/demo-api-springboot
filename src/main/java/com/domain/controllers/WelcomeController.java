package com.domain.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/welcome")//memberi url/endpoint
public class WelcomeController {

    @GetMapping
    public String welcome() {
        return "Selamat datang farhan di Spring Boot";
    }

    @PostMapping
    public String other() {
        return "Data lain";
    }

}
