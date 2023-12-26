package com.ll.medium.domain.home.home.controller.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity //member entitiy 생성
public class Member {
    @Id // 고유 식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스가 값 자동으로 생성
    private Long id; // int 보다 더 많은 값을 저장 64bit
    // long은 값이 없을 경우 0으로 초기화, id가 없어서 0인지, 데이터 값이 0인지 구분 불가
    // Long 은 값이 없으면 null로 초기화

    @Column(unique = true) // 동일한 ID 중복 불가
    private String username;

    private String password;
}
