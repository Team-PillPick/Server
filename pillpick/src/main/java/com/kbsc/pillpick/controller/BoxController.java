package com.kbsc.pillpick.controller;

import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.service.BoxService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("box")
public class BoxController {

    private final BoxService boxService;
    @PostMapping()
    public void saveBox() throws IOException, ParseException {
        boxService.saveBox();
    }

    @GetMapping()
    public ResponseEntity<BasicResponse> readAllBoxes(){
        return boxService.readAllBoxes();
    }


}