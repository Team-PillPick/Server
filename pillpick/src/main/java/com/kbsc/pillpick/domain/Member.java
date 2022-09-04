package com.kbsc.pillpick.domain;

import com.kbsc.pillpick.domain.Medicine;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    // 유저 이메일
    private String email;

    // 프로필 이미지 주소
    private String profilePic;

    // 에티켓 레벨
    private int level;

    //반납 횟수
    private int count;

    // [Medicine] 1: N 양방향 관계
    @OneToMany(mappedBy = "member")
    private List<Medicine> medicineList = new ArrayList<>();
}