package com.example.demo.src.user2.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LogInReq {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
