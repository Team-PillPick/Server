package com.kbsc.pillpick.service;

import com.kbsc.pillpick.config.jwtConfig.JwtTokenProvider;
import com.kbsc.pillpick.domain.Member;
import com.kbsc.pillpick.domain.memberDto.MemberJoinRequestDto;
import com.kbsc.pillpick.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public Long join(MemberJoinRequestDto memberJoinRequestDto){
        if(memberRepository.findByEmail(memberJoinRequestDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
        }

        Member member = new Member(memberJoinRequestDto);

        memberRepository.save(member);
        return member.getId();
    }

    public String login(String email){

        log.info("로그인  이메일 : " + email);
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("가입되지 않은 이메일 입니다.")
        );

        log.info("멤버 이름 : " + member.getName());
        return jwtTokenProvider.createToken(member.getEmail(), member.getRoles());
    }
}