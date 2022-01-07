package com.example.demologin.controller;

import com.example.demologin.security.JwtTokenUtil;
import com.example.demologin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/api/info")
    public ResponseEntity<?> getUserInfo(){
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
