package com.ll.medium.domain.home.home.controller.article;

import com.ll.medium.domain.home.home.controller.comment.CommentForm;
import com.ll.medium.domain.home.home.controller.member.Member;
import com.ll.medium.domain.home.home.controller.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class ArticleController {
    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList", articleList);
        return "domain/home/home/article/article_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id, CommentForm answerForm) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "domain/home/home/article/article_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    public String articleWrite(ArticleForm articleForm) {
        return "domain/home/home/article/article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    public String articleWrite(@Valid ArticleForm articleForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "domain/home/home/aritcle/article_form";
        }
        Member member = this.memberService.getMember(principal.getName());
        this.articleService.write(articleForm.getTitle(), articleForm.getBody(), member);
        return "redirect:/post/list";
    }
}
