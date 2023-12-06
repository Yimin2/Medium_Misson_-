package com.ll.medium.domain.home.home.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // userRepository를 사용하여 User 데이터를 생성하는 join 메서드
    public SiteMember join(String username, String password) {
        SiteMember member = new SiteMember();
        member.setUsername(username);
        // 시큐리티 암호화 사용
        member.setPassword(passwordEncoder.encode(password));
        // BCryptPasswordEncoder 객체를 직접 생성하지 않고, 빈으로 등록 후 객체를 주입받아 사용
        this.memberRepository.save(member);
        return member;
    }
}
