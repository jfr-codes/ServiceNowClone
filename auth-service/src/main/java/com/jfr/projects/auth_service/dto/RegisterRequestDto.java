package com.jfr.projects.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestDto {

    private String id;
    private String firstName;
    private String lastName;
    private String position;
    private String username;
    private String password;
    private String role;
}
