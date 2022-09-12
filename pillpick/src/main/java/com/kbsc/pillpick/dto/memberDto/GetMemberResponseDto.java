

package com.kbsc.pillpick.dto.memberDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetMemberResponseDto { //홈 정보

    private String name;
    private int count;
    private int level;
}