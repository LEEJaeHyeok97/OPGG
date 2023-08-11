package com.example.demo.src.user2.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FindTeamByNicknameReq {
    @NotBlank
    private String nickname;
}
