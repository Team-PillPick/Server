package com.kbsc.pillpick.controller;

import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.domain.Member;
import com.kbsc.pillpick.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping()
    public ResponseEntity<BasicResponse> readHomeInfo(@AuthenticationPrincipal Member member){
        return homeService.readHomeInfo(member);
    }

}
