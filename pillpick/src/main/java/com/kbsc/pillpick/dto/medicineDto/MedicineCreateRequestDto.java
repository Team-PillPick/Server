package com.kbsc.pillpick.dto.medicineDto;


import lombok.Getter;
@Getter
public class MedicineCreateRequestDto {
    private String name;
    private String form;
    private String expirationDate;
}
