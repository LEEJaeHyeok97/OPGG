package com.example.demo.src.user2.application;

import com.example.demo.global.payload.ApiResponse;
import com.example.demo.global.payload.ErrorResponse;
import com.example.demo.global.payload.Message;
import com.example.demo.src.user2.domain.Member;
import com.example.demo.src.user2.domain.repository.User2Repository;
import com.example.demo.src.user2.dto.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final User2Repository user2Repository;

    @Transactional
    public ResponseEntity<?> signUp(SignUpReq signUpRequest) {
        Member member = Member.builder()
                .email(signUpRequest.getEmail())
                .nickname(signUpRequest.getNickname())
                .password(signUpRequest.getPassword())
                .supportingTeam(signUpRequest.getSupportingTeam())
                .build();

        user2Repository.save(member);

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(Message.builder().message("회원가입에 성공했습니다.").build())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    public ResponseEntity<?> logIn(LogInReq logInReq, HttpSession session) {
        Optional<Member> byEmail = user2Repository.findByEmail(logInReq.getEmail());
        if (byEmail.isPresent()) {
            Member member = byEmail.get();
            if (member.getPassword().equals(logInReq.getPassword())) {
                LogInRes logInRes = LogInRes.builder()
                        .email(member.getEmail())
                        .password(member.getPassword())
                        .build();

                ApiResponse apiResponse = ApiResponse.builder()
                        .check(true)
                        .information(Message.builder().message("로그인에 성공했습니다.").build())
                        .build();

                session.setAttribute("loginEmail", logInReq.getEmail());
                System.out.println("session = " + session.getAttribute("loginEmail")); //세션 저장 콘솔창에 테스트
                return ResponseEntity.ok(apiResponse);
            } else { // 비밀번호 불일치
                ErrorResponse errorResponse = ErrorResponse.builder()
                        .check(false)
                        .error(Message.builder().message("비밀번호가 불일치합니다.").build())
                        .build();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } else { // 조회 결과 없음(회원 정보에 없음)
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .check(false)
                    .error(Message.builder().message("회원 조회 결과가 없습니다.").build())
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

    }

    public ResponseEntity<?> findTeamByNickname(FindTeamByNicknameReq findTeamByNicknameReq) {
        Optional<Member> userWhichSupportingTeam = user2Repository.findUserWhichSupportingTeam(findTeamByNicknameReq.getNickname());

        if (userWhichSupportingTeam.isPresent()) {
            Member member = userWhichSupportingTeam.get();
            FindTeamByNicknameRes findTeamByNicknameRes = FindTeamByNicknameRes.builder().
            nickname(member.getNickname()).
            supportingTeam(member.getSupportingTeam()).
            build();

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(Message.builder().message(findTeamByNicknameRes.getSupportingTeam()).build())
                    .build();

            return ResponseEntity.ok(apiResponse);

        } else {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .check(false)
                    .error(Message.builder().message("닉네임 조회 결과가 없습니다.").build())
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

//    public List<Member> findAllUsers() {
//        List<Member> memberList = user2Repository.findAllEmails();
//
//    }
}
