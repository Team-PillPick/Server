package com.kbsc.pillpick.domain.member.repository;

import com.kbsc.pillpick.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
