package com.kbsc.pillpick.domain;

import com.kbsc.pillpick.dto.medicineDto.CreateMedicineRequestDto;
import com.kbsc.pillpick.dto.medicineDto.UpdateMedicineRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Medicine extends Timestamped {

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

    public Medicine(Member member, CreateMedicineRequestDto requestDto) {
        this.name = requestDto.getName();
        this.form = requestDto.getForm();
        this.expirationDate = requestDto.getExpirationDate();
        this.member = member;
    }
    public void updateMedicine(UpdateMedicineRequestDto requestDto) {
        this.name = requestDto.getName();
        this.form = requestDto.getForm();
        this.expirationDate = requestDto.getExpirationDate();
    }
}