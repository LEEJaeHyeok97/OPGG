package com.example.demo.src.user2.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FindTeamByNicknameRes {
    @NotBlank
    private String nickname;

    private String supportingTeam;

    @Builder
    public FindTeamByNicknameRes(String nickname, String supportingTeam) {
        this.nickname = nickname;
        this.supportingTeam = supportingTeam;
    }
}
