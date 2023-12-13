package com.ll.medium.domain.home.home.controller.article;

import com.ll.medium.domain.home.home.controller.comment.CommentForm;
import com.ll.medium.domain.home.home.controller.member.Member;
import com.ll.medium.domain.home.home.controller.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myList")
    public String myList(Model model, Principal principal) {
        String username = principal.getName();
        List<Article> myList = articleService.findByUsername(username);
        model.addAttribute("articleList", myList);
        return "domain/home/home/article/article_list";
    }

    @GetMapping("/userArticles")
    public String viewUserArticles(@RequestParam(required = false) String username, Model model) {
        if (username != null && !username.isEmpty()) {
            List<Article> articles = articleService.findByUsername(username);
            model.addAttribute("articleUserList", articles);
        }
        return "domain/home/home/article/user_articles"; // 검색된 게시글을 보여주는 뷰
    }

    @GetMapping(value = "/{id}")
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
            return "domain/home/home/article/article_form";
        }
        Member member = this.memberService.getMember(principal.getName());
        this.articleService.write(articleForm.getTitle(), articleForm.getBody(), member);
        return "redirect:/post/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/modify")
    public String articleModify(ArticleForm articleForm, @PathVariable("id") Long id, Principal principal) {
        Article article = this.articleService.getArticle(id);
        if(!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다.");
        }
        articleForm.setTitle(article.getTitle());
        articleForm.setBody(article.getBody());
        return "domain/home/home/article/article_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/modify")
    public String articleModify(@Valid ArticleForm articleForm, BindingResult bindingResult, @PathVariable("id") Long id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "domain/home/home/article/article_form";
        }
        Article article = this.articleService.getArticle(id);
        if(!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다.");
        }
        this.articleService.modify(article, articleForm.getTitle(), articleForm.getBody());
        return String.format("redirect:/post/%s", id);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/delete")
    public String articleDelete(Principal principal, @PathVariable("id") Long id) {
        Article article = this.articleService.getArticle(id);
        if (!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.articleService.delete(article);
        return "redirect:/post/list";
    }

}
