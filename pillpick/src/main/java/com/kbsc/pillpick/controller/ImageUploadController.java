package com.kbsc.pillpick.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.kbsc.pillpick.service.ImageUploadService;

@RequiresArgsConstructor
@RestController
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @PostMapping("/mypill/upload")
    public String uploadImage(@RequestPart MultipartFile file){
        return imageUploadService.uploadImage(file);
    }
}