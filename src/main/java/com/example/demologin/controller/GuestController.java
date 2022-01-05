package com.example.demologin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class GuestController {

    //Login
    @GetMapping("")
    public ResponseEntity<?> Login(String username, String password){
        return null;
    }

    @PostMapping("/signup")
    public String Signup(){
        return "index";
    }
}
