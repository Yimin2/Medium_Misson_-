package com.ll.medium.domain.home.home.controller.member;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinForm {
    @Size(min = 4, max = 25, message = "ID를 4 ~ 25자로 설정해주세요." ) // username 길이는 4~25 사이
    @NotEmpty(message =  "사용자ID는 필수항목입니다.")
    private String username;

    @Size(min = 8, message = "비밀번호를 최소 8자 이상 설정해주세요." )
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String passwordConfirm;
}
