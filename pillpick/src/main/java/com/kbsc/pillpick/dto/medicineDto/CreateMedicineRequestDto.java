package com.kbsc.pillpick.dto.medicineDto;


import lombok.Getter;

import lombok.Setter;
@Getter
@Setter
public class CreateMedicineRequestDto {
    private String name;
    private String form;
    private String expirationDate;

    private String memberId;
    private String[] attachedPhotoIds;
}
