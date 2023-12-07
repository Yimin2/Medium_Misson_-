package com.ll.medium.domain.home.home.controller.member;

import lombok.Getter;

@Getter // 상수형 자료형이라 setter 제외
public enum MemberRole { // enum 열거 자료형
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    MemberRole(String value) {
        this.value = value;
    }

    private String value;
}
