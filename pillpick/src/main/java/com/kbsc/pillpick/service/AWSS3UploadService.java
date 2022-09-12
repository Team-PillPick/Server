package com.kbsc.pillpick.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.kbsc.pillpick.dto.component.S3Component;

import com.kbsc.pillpick.repository.MedicineRepository;
import com.kbsc.pillpick.domain.Medicine;

import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class AWSS3UploadService implements UploadService{

    private final AmazonS3 amazonS3;
    private final S3Component component;

    private String fileUrl;

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName){
        amazonS3.putObject(new PutObjectRequest(component.getBucket(), fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }
    @Override
    public String getFileUrl(String fileName) {
        fileUrl = amazonS3.getUrl(component.getBucket(), fileName).toString();
        return amazonS3.getUrl(component.getBucket(), fileName).toString();
    }

    private final MedicineRepository medicineRepository;

//    public Medicine save(final Long id) {
//        Medicine medicine = medicineRepository.findById(id);
//        medicine.changePhoto(fileUrl);
//        return medicine;
//    }
}