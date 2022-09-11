package com.kbsc.pillpick.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;

    @NotNull
    private String attachedPhoto;

    // [Member] 1: N 양방향 관계 (연관관계 주인)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 임시 데이터 저장을 위한 생성자
    public Photo(String attached, Member member) {
        this.attachedPhoto = attached;
        this.member = member;
    }

}

