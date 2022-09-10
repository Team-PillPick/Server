package com.kbsc.pillpick.service;

import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.domain.Medicine;
import com.kbsc.pillpick.domain.Member;
import com.kbsc.pillpick.dto.medicineDto.DeleteMedicineRequestDto;
import com.kbsc.pillpick.dto.medicineDto.GetMedicineResponseDto;
import com.kbsc.pillpick.dto.medicineDto.CreateMedicineRequestDto;
import com.kbsc.pillpick.dto.medicineDto.UpdateMedicineRequestDto;
import com.kbsc.pillpick.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    private final PhotoRepository photoRepository;

    public ResponseEntity<BasicResponse> createMyPill(Member member, CreateMedicineRequestDto requestDto) {
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

            if(reviewDTO.getAttachedPhotoIds() != null){
                // 리뷰 사진 DB 저장
                for (String attached : reviewDTO.getAttachedPhotoIds()) {
                    Photo photo = new Photo(attached, review);
                    photoRepository.save(photo);
                }
            }

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
                    .message("나의 약품 전제 조회 성공")
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


    @Transactional
    public ResponseEntity<BasicResponse> updateMyPill(Long pillId, UpdateMedicineRequestDto requestDto) {

        BasicResponse basicResponse = new BasicResponse();
        HttpStatus httpStatus = null;

        Optional<Medicine> targetMedicine = medicineRepository.findById(pillId);

        if(targetMedicine.isPresent()){
            httpStatus = HttpStatus.CREATED;
            targetMedicine.get().updateMedicine(requestDto);

            basicResponse = BasicResponse.builder()
                    .status(HttpStatus.CREATED.value())
                    .message("약품 편집 성공")
                    .data(Collections.emptyList())
                    .success(true)
                    .build();
        }else{
            httpStatus = HttpStatus.BAD_REQUEST;
            basicResponse = BasicResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("관련 약품이 없습니다.")
                    .data(Collections.emptyList())
                    .success(false)
                    .build();
        }

        return new ResponseEntity<>(basicResponse, httpStatus);
    }

    public ResponseEntity<BasicResponse> deleteMyPill(List<Long> pillIdList) {

        BasicResponse basicResponse = new BasicResponse();
        HttpStatus httpStatus = null;


        for (Long pillId : pillIdList) {
            Optional<Medicine> targetMedicine = medicineRepository.findById(pillId);
            if(targetMedicine.isEmpty()){
                throw new IllegalArgumentException("존재하지 않는 약품 입니다 : " + pillId);
            }
            medicineRepository.deleteById(pillId);
        }

        httpStatus = HttpStatus.OK;

        basicResponse = BasicResponse.builder()
                .status(HttpStatus.OK.value())
                .message("약품 삭제 성공")
                .data(Collections.emptyList())
                .success(true)
                .build();

        return new ResponseEntity<>(basicResponse, httpStatus);
    }
}