package com.jfr.projects.auth_service.controller;


import com.jfr.projects.auth_service.dto.LoginRequestDto;
import com.jfr.projects.auth_service.dto.RegisterRequestDto;
import com.jfr.projects.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto){
        System.out.println("Login HIT");
        return ResponseEntity.ok(authService.login(loginRequestDto));

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) throws Exception{
        return ResponseEntity.ok(authService.register(registerRequestDto));
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Healthy.";
    }

}
