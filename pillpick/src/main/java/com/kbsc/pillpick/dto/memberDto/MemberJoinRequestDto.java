package com.kbsc.pillpick.dto.memberDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJoinRequestDto {

    private String name;
    private String email;
}
