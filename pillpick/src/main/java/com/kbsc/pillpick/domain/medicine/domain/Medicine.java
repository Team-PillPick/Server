package com.kbsc.pillpick.domain.medicine.domain;

import com.kbsc.pillpick.domain.member.domain.Member;

import javax.persistence.*;

@Entity
public class Medicine {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "medicine_name")
    private String name;

    // 약 형태
    private String form;

    // 유효기간
    private String expirationDate;

    // 대표 이미지 주소
    private String photo;

    // [Member] 1: N 양방향 관계 (연관관계 주인)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}