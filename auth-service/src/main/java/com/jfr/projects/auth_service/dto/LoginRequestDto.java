package com.jfr.projects.auth_service.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    String username;
    String password;
}
