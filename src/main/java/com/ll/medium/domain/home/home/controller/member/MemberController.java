package com.ll.medium.domain.home.home.controller.member;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(MemberJoinForm memberJoinForm) {
        return "domain/home/home/join_form";
    }

    @PostMapping("/join")
    public String join(@Valid MemberJoinForm memberJoinForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "domain/home/home/join_form";
        }

        if (!memberJoinForm.getPassword().equals(memberJoinForm.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordconfirm", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "domain/home/home/join_form";
        }

        memberService.join(memberJoinForm.getUsername(), memberJoinForm.getPassword());

        return "redirect:/";
    }
}