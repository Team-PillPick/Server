package com.kbsc.pillpick.repository;

import java.util.Collection;
import java.util.List;

import com.kbsc.pillpick.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
}