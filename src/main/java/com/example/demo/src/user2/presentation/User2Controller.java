package com.example.demo.src.user2.presentation;

import com.example.demo.src.user2.application.MemberService;
import com.example.demo.src.user2.domain.Member;
import com.example.demo.src.user2.dto.FindTeamByNicknameReq;
import com.example.demo.src.user2.dto.LogInReq;
import com.example.demo.src.user2.dto.SignUpReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("opgg/user")
public class User2Controller {
    private final MemberService memberService;

    //회원가입
    @PostMapping("/signUp")
    public ResponseEntity<?> singUp(@Valid @RequestBody SignUpReq signUpRequest) {
        return memberService.signUp(signUpRequest);
    }

    //로그인
    @PostMapping("/logIn")
    public ResponseEntity<?> login(@Valid @RequestBody LogInReq logInReq, HttpSession session) {
        return memberService.logIn(logInReq, session);
    }

    //닉네임으로 유저가 지지하는 팀 찾기
    //JPQL 사용과제 완료
    @GetMapping("/findTeamByNickname")
    public ResponseEntity<?> findTeamByNickname(@Valid @RequestBody FindTeamByNicknameReq findTeamByNicknameReq) {
        return memberService.findTeamByNickname(findTeamByNicknameReq);
    }

    //닉네임 변경

    //계정 즉시 삭제

    //계정 휴면 상태 변경

    //회원 검색

    //가입한 모든 계정 조회
//    @GetMapping("/findAllUsers")
//    public List<Member> findAllUsers() {
//        return memberService.findAllUsers();
//    }
}
