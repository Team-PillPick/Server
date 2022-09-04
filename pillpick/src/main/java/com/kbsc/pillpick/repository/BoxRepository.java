package com.kbsc.pillpick.repository;

import com.kbsc.pillpick.domain.box.domain.Box;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxRepository extends JpaRepository<Box, Long> {
}
