package com.kbsc.pillpick.service;

import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.domain.Medicine;
import com.kbsc.pillpick.domain.Member;
import com.kbsc.pillpick.dto.medicineDto.GetMedicineResponseDto;
import com.kbsc.pillpick.dto.medicineDto.MedicineCreateRequestDto;
import com.kbsc.pillpick.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public ResponseEntity<BasicResponse> readAllMyPill(Member member) {

        BasicResponse basicResponse = new BasicResponse();
        HttpStatus httpStatus = null;

        if(member != null){
            httpStatus = HttpStatus.OK;
            List<Medicine> myMedicineList = medicineRepository.findByMember(member);
            List<GetMedicineResponseDto> dataList = new ArrayList<>();

            for (Medicine myMedicine : myMedicineList) {
                dataList.add(GetMedicineResponseDto.builder()
                        .id(myMedicine.getId())
                        .form(myMedicine.getForm())
                        .expirationDate(myMedicine.getExpirationDate())
                        .name(myMedicine.getName())
                        .photo(myMedicine.getPhoto())
                        .build());
            }

            basicResponse = BasicResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("마이필 뷰 전체 조회 성공")
                    .data(Arrays.asList(dataList))
                    .success(true)
                    .build();
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