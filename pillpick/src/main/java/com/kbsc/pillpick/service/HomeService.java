package com.kbsc.pillpick.service;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final HomeRepository homeRepository;

    public ResponseEntity<BasicResponse> readHomeInfo(String name) {

        BasicResponse basicResponse = new BasicResponse();
        HttpStatus httpStatus = null;

        if(member != null){
            httpStatus = HttpStatus.OK;
            List<Member> memberList = memberRepository.findByName(name);
            List<GetMemberResponseDto> dataList = new ArrayList<>();

            for (Member member : memberList) {
                dataList.add(GetMemberResponseDto.builder()
                        .name(member.getName())
                        .count(member.getCount())
                        .level(member.getLevel())
                        .build());
            }

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

