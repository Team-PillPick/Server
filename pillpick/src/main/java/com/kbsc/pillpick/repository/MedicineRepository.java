package com.kbsc.pillpick.repository;

import com.kbsc.pillpick.domain.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}