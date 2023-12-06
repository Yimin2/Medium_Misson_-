package com.ll.medium.domain.home.home.controller.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class SiteMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // 동일한 ID 중복 불가
    private String username;

    private String password;
}
