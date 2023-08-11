package com.example.demo.src.user2.domain;

import com.example.demo.src.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member_table")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String supportingTeam;

//    @Column
//    private String status;

    @Builder
    public Member(Long id, String nickname, String email, String password, String supportingTeam) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.supportingTeam = supportingTeam;
    }
}
