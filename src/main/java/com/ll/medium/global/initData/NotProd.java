package com.ll.medium.global.initData;

import com.ll.medium.domain.home.home.controller.article.ArticleService;
import com.ll.medium.domain.home.home.controller.member.Member;
import com.ll.medium.domain.home.home.controller.member.MemberService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;

@Configuration
public class NotProd {
    @Bean
    public ApplicationRunner initNotProd(
            MemberService memberService,
            ArticleService articleService
    ) {
        return args -> {
            // 샘플 회원 데이터 생성
            Member memberUser0 = memberService.join("admin", "1234", true);
            Member memberUser1 = memberService.join("user1", "1234", false);
            IntStream.rangeClosed(2, 100).forEach(i -> {
                boolean isPaid = i > 50;
                memberService.join("user" + i, "1234", isPaid);
            });
            // 샘플 게시글 데이터 생성
            IntStream.rangeClosed(1, 100)
                    .forEach(i -> {
                        boolean isPaid = i > 50;
                        articleService.write(
                                "제목%d".formatted(i),
                                "내용%d".formatted(i),
                                memberUser1,
                                isPaid
                        );
                    });
        };
    }
}