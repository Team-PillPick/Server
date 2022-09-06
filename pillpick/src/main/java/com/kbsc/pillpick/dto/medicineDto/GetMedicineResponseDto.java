package com.kbsc.pillpick.dto.medicineDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetMedicineResponseDto {

    private Long id;
    private String photo;
    private String form;
    private String name;
    private String expirationDate;
}
