package com.ll.medium.domain.home.home.controller.member;

import com.ll.medium.domain.home.home.controller.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // userRepository를 사용하여 Member 데이터를 생성하는 join 메서드
    public Member join(String username, String password) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        // 시큐리티 암호화 사용
        // BCryptPasswordEncoder 객체를 직접 생성하지 않고, 빈으로 등록 후 객체를 주입받아 사용
        this.memberRepository.save(member);
        return member;
    }

    public Member getMember(String username) {
        Optional<Member> member = this.memberRepository.findByusername(username);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    public boolean hasPaidAccess(Principal principal) {
        if (principal == null) {
            return false;
        }

        Authentication authentication = (Authentication) principal;

        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("ROLE_PAID_MEMBER") ||
                                grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }

    public Member modify(Member member, Boolean isPaid) {
        member.setIsPaid(isPaid);
        this.memberRepository.save(member);
        return member;
    }
}
