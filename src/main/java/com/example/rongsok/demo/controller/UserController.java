package com.example.rongsok.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rongsok.demo.security.JwtTokenProvider;
import com.example.rongsok.demo.security.JwtAuthenticationResponse;
import com.example.rongsok.demo.model.LoginRequest;





@RestController
@RequestMapping("/api/auth")
public class UserController {

 
    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    

    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

       String username = loginRequest.getUsernameOrEmail();
      
        String jwt = tokenProvider.generateToken(username);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}