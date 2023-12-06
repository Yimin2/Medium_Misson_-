package com.ll.medium.domain.home.home.controller.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(MemberJoinForm memberJoinForm) {
        return "singup_form";
    }
    @PostMapping("/singup")
    public String signup(@Valid MemberJoinForm memberJoinForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        if (!memberJoinForm.getPassword().equals(memberJoinForm.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm","passwordInCorrect","2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        memberService.join(memberJoinForm.getUsername(), memberJoinForm.getPassword());

        return "redirect:/";
    }
}
