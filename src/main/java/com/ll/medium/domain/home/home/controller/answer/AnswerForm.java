package com.ll.medium.domain.home.home.controller.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
    @NotEmpty(message = "내용을 입력해 주세요.")
    private String body;
}
