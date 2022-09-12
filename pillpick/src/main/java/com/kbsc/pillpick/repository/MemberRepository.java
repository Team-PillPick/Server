package com.kbsc.pillpick.repository;

import com.kbsc.pillpick.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

//    @Modifying(clearAutomatically=true)
//    int updatePhoto(@Param(value="photo") String photo, @Param(value="id") Long id);

}