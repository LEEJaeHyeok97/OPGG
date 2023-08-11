package com.example.demo.src.user2.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LogInRes {

    private String email;

    private String password;

    private String token;

    @Builder
    public LogInRes(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }
}
