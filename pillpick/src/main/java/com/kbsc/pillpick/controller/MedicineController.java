package com.kbsc.pillpick.controller;

import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.domain.Member;
import com.kbsc.pillpick.dto.medicineDto.CreateMedicineRequestDto;
import com.kbsc.pillpick.dto.medicineDto.UpdateMedicineRequestDto;
import com.kbsc.pillpick.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mypill")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping("/post")
    public ResponseEntity<BasicResponse> createMyPill(@AuthenticationPrincipal Member member, @RequestBody CreateMedicineRequestDto requestDto) {
        return medicineService.createMyPill(member, requestDto);
    }

    @GetMapping()
    public ResponseEntity<BasicResponse> readAllMyPill(@AuthenticationPrincipal Member member) {
        return medicineService.readAllMyPill(member);
    }

    @PostMapping("/post/{pillId}")
    public ResponseEntity<BasicResponse> updateMyPill(@PathVariable Long pillId, @RequestBody UpdateMedicineRequestDto requestDto) {
        return medicineService.updateMyPill(pillId, requestDto);
    }
}


