package com.jfr.projects.auth_service.service;

import com.jfr.projects.auth_service.dto.LoginRequestDto;
import com.jfr.projects.auth_service.dto.RegisterRequestDto;
import com.jfr.projects.auth_service.entity.User;
import com.jfr.projects.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public String login(LoginRequestDto loginRequestDto){
        System.out.println("Login method called");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );
        System.out.println("Authenticated successfully");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtService.generateToken(userDetails);
    }

    public String register(RegisterRequestDto registerRequestDto) throws Exception{

        if(userRepository.existsByUsername(registerRequestDto.getUsername())){
            throw new Exception("Username already exists");
        }

        String encodedPassword = passwordEncoder.encode(registerRequestDto.getPassword());
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setPosition(registerRequestDto.getPosition());
        user.setRole(registerRequestDto.getRole());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "success";
    }



}
