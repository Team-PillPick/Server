package com.kbsc.pillpick.service;

import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.domain.Member;
import com.kbsc.pillpick.dto.memberDto.GetMemberResponseDto;
import com.kbsc.pillpick.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MemberRepository memberRepository;

    public ResponseEntity<BasicResponse> readHomeInfo(Member member) {

        BasicResponse basicResponse = new BasicResponse();
        HttpStatus httpStatus = null;

        if(member != null){
            httpStatus = HttpStatus.OK;
            Optional<Member> foundMember = memberRepository.findByEmail(member.getEmail());
            List<GetMemberResponseDto> dataList = new ArrayList<>();

            dataList.add(GetMemberResponseDto.builder()
                    .name(foundMember.get().getName())
                    .count(foundMember.get().getCount())
                    .level(foundMember.get().getLevel())
                    .build());

            basicResponse = BasicResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("홈화면 조회 성공")
                    .data(Arrays.asList(dataList))
                    .success(true)
                    .build();
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
            basicResponse = BasicResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("존재하지 않는 데이터입니다.")
                    .data(Collections.emptyList())
                    .success(false)
                    .build();
        }

        return new ResponseEntity<>(basicResponse, httpStatus);

    }
}