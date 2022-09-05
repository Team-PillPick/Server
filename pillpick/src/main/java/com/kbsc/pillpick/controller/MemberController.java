package com.kbsc.pillpick.controller;

import com.kbsc.pillpick.domain.memberDto.MemberJoinRequestDto;
import com.kbsc.pillpick.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("join")
    public Long join(@RequestBody MemberJoinRequestDto requestDto) {
        return memberService.join(requestDto);
    }

    // 로그인
    @PostMapping("login")
    public String login(@RequestParam(value = "email") String email){
        return memberService.login(email);
    }

}