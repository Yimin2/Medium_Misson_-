package com.ll.medium.domain.home.home.controller.member;

import lombok.Getter;

@Getter // 상수형 자료형이라 setter 제외
public class MemberRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
