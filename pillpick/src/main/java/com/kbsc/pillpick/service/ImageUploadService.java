package com.kbsc.pillpick.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.domain.Medicine;
import com.kbsc.pillpick.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kbsc.pillpick.service.UploadService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageUploadService {

    private final UploadService s3Service;
    private final MedicineRepository medicineRepository;

    @Transactional
    public ResponseEntity<BasicResponse> uploadImage(MultipartFile file, Long pillId) {
        BasicResponse basicResponse = new BasicResponse();
        HttpStatus httpStatus = null;

        String fileName = CreateFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try(InputStream inputStream = file.getInputStream()) {
            s3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생했습니다.", file.getOriginalFilename()));
        }
        String imgUrl = s3Service.getFileUrl(fileName);
        Optional<Medicine> targetMedicine = medicineRepository.findById(pillId);
        targetMedicine.get().updatePhoto(imgUrl);

        httpStatus = HttpStatus.OK;

        List<Object> dataList = new ArrayList<>();
        dataList.add(imgUrl);

        basicResponse = BasicResponse.builder()
                .status(HttpStatus.OK.value())
                .message("이미지 업로드 성공")
                .data(dataList)
                .success(true)
                .build();

        return new ResponseEntity<>(basicResponse, httpStatus);
    }

    private String CreateFileName(String originalFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    private String getFileExtension(String fileName) {
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e){
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s)입니다", fileName));
        }
    }


}