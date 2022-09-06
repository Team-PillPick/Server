package com.kbsc.pillpick.dto.medicineDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMedicineRequestDto {
    private String name;
    private String form;
    private String expirationDate;
}
