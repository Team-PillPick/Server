package com.kbsc.pillpick.repository;

import com.kbsc.pillpick.domain.Photo;
import com.kbsc.pillpick.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findByMember(Member member);
}

