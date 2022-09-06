package com.kbsc.pillpick.common.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BasicResponse {

    private Integer status;
    private String message;
    private List<Object> data;
    private Boolean success;
}
