package com.kbsc.pillpick.service;

import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.domain.Medicine;
import com.kbsc.pillpick.domain.Member;
import com.kbsc.pillpick.dto.MedicineCreateRequestDto;
import com.kbsc.pillpick.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public ResponseEntity<BasicResponse> createMyPill(Member member, MedicineCreateRequestDto requestDto) {
        BasicResponse basicResponse = new BasicResponse();
        HttpStatus httpStatus = null;


        if(member != null){
            httpStatus = HttpStatus.OK;

            basicResponse = BasicResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("약품이 등록됐습니다.")
                    .data(Collections.emptyList())
                    .success(true)
                    .build();

            Medicine createdMedicine = new Medicine(member, requestDto);
            Long medicineId = medicineRepository.save(createdMedicine).getId();
        }else{
            httpStatus = HttpStatus.NOT_FOUND;

            basicResponse = BasicResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("사용자를 찾을 수 없습니다.")
                    .data(Collections.emptyList())
                    .success(false)
                    .build();
        }

        return new ResponseEntity<>(basicResponse, httpStatus);
    }

}