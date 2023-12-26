package com.ll.medium.domain.home.home.controller.comment;

import com.ll.medium.domain.home.home.controller.article.Article;
import com.ll.medium.domain.home.home.controller.article.ArticleService;
import com.ll.medium.domain.home.home.controller.member.Member;
import com.ll.medium.domain.home.home.controller.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@RequestMapping("/post/")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/comment/write")
    public String writeAnswer(@PathVariable("id") Long id, @RequestParam(value="content") String body, Principal principal) {
        Article article = this.articleService.getArticle(id);
        Member member = this.memberService.getMember(principal.getName());
        this.commentService.write(article, body, member);
        return String.format("redirect:/post/%s", id);
    }
}