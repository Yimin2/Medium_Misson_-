package com.ll.medium.domain.home.home.controller.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor // 생성자 자동 생성
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
        // 회원 정보 검사, join 메서드의 회원정보를 @Valid로 객체 유효성 검사, BindingResult로 검사 결과 저장
        if (bindingResult.hasErrors()) {
            // 객체 유효성 검사, 오류가 있으면 가입페이지 리턴
            return "domain/home/home/join_form";
        }
        if (!memberJoinForm.getPassword().equals(memberJoinForm.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordconfirm", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            // password와 passwordConfirm 일치하지 않으면 메세지와 함께 가입페이지 리턴
            return "domain/home/home/join_form";
        }
        memberService.join(memberJoinForm.getUsername(), memberJoinForm.getPassword());
        // 회원가입 처리
        return "redirect:/";
        //성공시 기본 화면으로 리턴
    }

    @GetMapping("/login")
    public String login() {
        return "domain/home/home/login_form";
    }
    // POST는 security에서 처리
}