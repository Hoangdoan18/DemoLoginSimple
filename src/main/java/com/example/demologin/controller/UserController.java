package com.example.demologin.controller;

import com.example.demologin.entity.User;
import com.example.demologin.model.mapper.UserMapper;
import com.example.demologin.model.request.AuthenticateReq;

import com.example.demologin.model.request.CreateUserReq;
import com.example.demologin.security.CustomUserDetails;
import com.example.demologin.security.JwtTokenUtil;
import com.example.demologin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
