package com.kbsc.pillpick.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping()
    public ResponseEntity<BasicResponse> readHomeInfo(@AuthenticationPrincipal Member member){
        return homeService.readHomeInfo(name);
    }

}