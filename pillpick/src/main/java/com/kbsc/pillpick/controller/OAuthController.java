package com.kbsc.pillpick.controller;

import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.service.MemberService;
import com.kbsc.pillpick.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthService oAuthService;
    private final MemberService memberService;

    @GetMapping("/kakao")
    public ResponseEntity<BasicResponse> kakaoLoginAndJoin(@RequestParam String code) {

        BasicResponse basicResponse = new BasicResponse();
        HttpStatus httpStatus = null;
        String jwt = "";
//        log.info(code);
//        String accessToken = oAuthService.getKakaoAccessToken(code);
//        System.out.println("accessToken : " + accessToken);
        
        String accessToken = code;

        try{
            HashMap<String, Object> userInfo = oAuthService.getUserInfo(accessToken);
            String nickname = (String) userInfo.get("nickname");
            String email = (String) userInfo.get("email");
            log.info("nickname : " + nickname);
            log.info("email : " + email);

            jwt = oAuthService.kakaoJoinAndLogin(nickname, email);
            log.info("jwt = " + jwt);
        }catch(Exception e){
            httpStatus = HttpStatus.BAD_REQUEST;

            basicResponse = BasicResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("잘못된 엑세스 토큰입니다.")
                    .data(Collections.emptyList())
                    .success(false)
                    .build();

            return new ResponseEntity<>(basicResponse, httpStatus);
        }

        httpStatus = HttpStatus.OK;

        basicResponse = BasicResponse.builder()
                .status(HttpStatus.OK.value())
                .message("카카오 로그인 성공")
                .data(Collections.singletonList(jwt))
                .success(true)
                .build();



        return new ResponseEntity<>(basicResponse, httpStatus);
    }
}

