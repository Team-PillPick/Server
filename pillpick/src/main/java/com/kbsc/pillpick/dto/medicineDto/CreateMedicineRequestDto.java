package com.kbsc.pillpick.dto.medicineDto;


import lombok.Getter;
@Getter
public class CreateMedicineRequestDto {
    private String name;
    private String form;
    private String expirationDate;
}
