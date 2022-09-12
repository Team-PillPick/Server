package com.kbsc.pillpick.controller;

import com.kbsc.pillpick.common.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.kbsc.pillpick.service.ImageUploadService;

@RequiredArgsConstructor
@RestController
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @PostMapping("/mypill/upload/{pillId}")
    public ResponseEntity<BasicResponse> uploadImage(@RequestPart MultipartFile file, @PathVariable Long pillId){
        return imageUploadService.uploadImage(file, pillId);
    }
}