package com.ll.medium.domain.home.home.controller.member;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinForm {
    @Size(min = 3, max = 25) // username 길이는 3~25 사이
    @NotEmpty(message = "사용자ID는 필수항목 입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목 입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수항목 입니다.")
    private String passwordConfirm;
}
