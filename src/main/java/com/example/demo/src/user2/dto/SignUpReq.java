package com.example.demo.src.user2.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class SignUpReq {


    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String supportingTeam;



}
