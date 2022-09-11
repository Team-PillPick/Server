package com.kbsc.pillpick.dto.boxDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoxResponseDto {
    private Long id;
    private String address;
    private String name;
    private float x;
    private float y;
}
