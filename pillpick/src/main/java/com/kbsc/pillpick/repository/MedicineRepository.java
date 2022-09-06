package com.kbsc.pillpick.repository;

import com.kbsc.pillpick.domain.Medicine;
import com.kbsc.pillpick.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findByMember(Member member);
}