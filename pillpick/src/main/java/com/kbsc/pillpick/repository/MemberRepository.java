package com.kbsc.pillpick.repository;

import com.kbsc.pillpick.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
}