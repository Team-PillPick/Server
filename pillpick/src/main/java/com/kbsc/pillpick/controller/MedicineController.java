package com.kbsc.pillpick.controller;

import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.domain.Member;
import com.kbsc.pillpick.dto.medicineDto.CreateMedicineRequestDto;
import com.kbsc.pillpick.dto.medicineDto.DeleteMedicineRequestDto;
import com.kbsc.pillpick.dto.medicineDto.UpdateMedicineRequestDto;
import com.kbsc.pillpick.service.MedicineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("mypill")
@RequiredArgsConstructor
@Slf4j
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

    @PutMapping("/post/{pillId}")
    public ResponseEntity<BasicResponse> updateMyPill(@PathVariable Long pillId, @RequestBody UpdateMedicineRequestDto requestDto) {
        return medicineService.updateMyPill(pillId, requestDto);
    }

    @DeleteMapping("/post")
    public ResponseEntity<BasicResponse> deleteMyPill(@RequestParam List<Long> pillIdList){
        System.out.println(pillIdList);
        return medicineService.deleteMyPill(pillIdList);
    }

    @PostMapping("/return")
    public ResponseEntity<BasicResponse> returnMyPill(@AuthenticationPrincipal Member member, @RequestParam List<Long> pillIdList){
        System.out.println(pillIdList);
        return medicineService.returnMyPill(member, pillIdList);
    }
}


