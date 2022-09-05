package com.kbsc.pillpick.controller;

import com.kbsc.pillpick.service.MemberService;
import com.kbsc.pillpick.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthService oAuthService;
    private final MemberService memberService;

    @GetMapping("/kakao")
    public String kakaoLoginAndJoin(@RequestParam String code) {
//        log.info(code);
//        String accessToken = oAuthService.getKakaoAccessToken(code);
//        System.out.println("accessToken : " + accessToken);
        
        String accessToken = code;

        HashMap<String, Object> userInfo = oAuthService.getUserInfo(accessToken);
        String nickname = (String) userInfo.get("nickname");
        String email = (String) userInfo.get("email");
        log.info("nickname : " + nickname);
        log.info("email : " + email);

        String jwt = "";
        jwt = oAuthService.kakaoJoinAndLogin(nickname, email);
        log.info("jwt = " + jwt);

        return jwt;
    }
}

