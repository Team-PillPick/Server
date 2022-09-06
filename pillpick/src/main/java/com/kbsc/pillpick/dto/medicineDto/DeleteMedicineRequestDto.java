package com.kbsc.pillpick.dto.medicineDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DeleteMedicineRequestDto {

    List<Long> medicineIdList;
}
