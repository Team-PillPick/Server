package com.kbsc.pillpick.domain.medicine.repository;

import com.kbsc.pillpick.domain.medicine.domain.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
