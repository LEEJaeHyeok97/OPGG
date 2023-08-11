package com.example.demo.src.user2.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LogInRes {

    private String email;

    private String password;

    @Builder
    public LogInRes(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
