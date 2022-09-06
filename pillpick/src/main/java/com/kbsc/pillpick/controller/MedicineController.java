package com.kbsc.pillpick.controller;

import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.domain.Member;
import com.kbsc.pillpick.dto.MedicineCreateRequestDto;
import com.kbsc.pillpick.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mypill")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping("/post")
    public ResponseEntity<BasicResponse> createMyPill(@AuthenticationPrincipal Member member, @RequestBody MedicineCreateRequestDto requestDto){
        return medicineService.createMyPill(member, requestDto);
    }
}


