package com.example.demologin.controller;

import com.example.demologin.entity.User;
import com.example.demologin.exception.DuplicateRecordException;
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

    @GetMapping("/hello")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> Login(@Valid @RequestBody AuthenticateReq req, HttpServletResponse response) {
        try {
            // Authenticate from email and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getEmail(),
                            req.getPassword()
                    )
            );
            // If no exception detected => valid info
            // Set Authenticate info to Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Generate token
            String token = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
            Cookie cookie = new Cookie("JWT_TOKEN", token.replace(" ", ""));
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok(token);
        } catch (Exception ex) {
            throw new DuplicateRecordException("Email or password invalid.");
        }
    }

    @PostMapping("/logout-test")
    public ResponseEntity<?> SignOut(){
        return ResponseEntity.ok("Sign out success.");
    }

    @PostMapping("/register")
    public ResponseEntity<?> Register(@Valid @RequestBody CreateUserReq req, HttpServletResponse response) {
        User result = userService.createUser(req);

        // Gen token
        UserDetails principal = new CustomUserDetails(result);
        String token = jwtTokenUtil.generateToken(principal);

        // Add token to cookie to login
        Cookie cookie = new Cookie("JWT_TOKEN",token.replace(" ", ""));
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(UserMapper.toUserDto(result));
    }
}
