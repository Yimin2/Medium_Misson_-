package com.ll.medium.domain.home.home.controller.article;

import com.ll.medium.domain.home.home.controller.member.Member;
import com.ll.medium.domain.home.home.controller.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class ArticleController {
    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Article> paging = this.articleService.getList(page); //Article 객체 리스트, 제목, 내용, 작성자 등 정보 포함
        model.addAttribute("paging", paging); //
        return "domain/home/home/article/article_list";
    }

    @PreAuthorize("isAuthenticated()") //인증(로그인)된 사용자만 접근 허용
    @GetMapping("/myList")
    public String myList(Model model, Principal principal,
                         @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Article> userPaging = articleService.findByUsername(principal.getName(), page); // 로그인된 사용자가 작성한 글 목록 가져옴
        model.addAttribute("paging", userPaging);
        return "domain/home/home/article/article_list";
    }

    @GetMapping("/b/{author}")
    public String userList(@PathVariable("author") String username, Model model,
                           @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Article> userPaging = articleService.findByUsername(username, page);
        model.addAttribute("paging", userPaging);
        return "domain/home/home/article/article_list";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("id") Long id, Principal principal) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        if (article.getAuthor().getUsername().equals(principal.getName())) {
            return "domain/home/home/article/article_detail";
        } else if (article.getIsPaid()) {
            if (!memberService.hasPaidAccess(principal)) {
                // 유료 회원 전용 게시글에 대한 접근 제한 처리
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "유료회원 전용 콘텐츠입니다.");
            } else {
                return "domain/home/home/article/article_detail";
            }
        } else {
            return "domain/home/home/article/article_detail";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    public String articleWrite(ArticleForm articleForm) {
        return "domain/home/home/article/article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    public String articleWrite(@Valid ArticleForm articleForm, BindingResult bindingResult, Principal principal) {
        // Valid 데이터 유효성 검사
        if (bindingResult.hasErrors()) {  // 오류 확인
            return "domain/home/home/article/article_form";
        }
        Member member = this.memberService.getMember(principal.getName()); // 로그인된 사용자의 member 객체 조회
        this.articleService.write(articleForm.getTitle(), articleForm.getBody(), member, articleForm.getIsPaid());
        return "redirect:/post/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/modify")
    public String articleModify(ArticleForm articleForm, @PathVariable("id") Long id, Principal principal) {
        Article article = this.articleService.getArticle(id);
        if (!article.getAuthor().getUsername().equals(principal.getName())) { // 로그인한 사용자와 게시글의 작성자 일치 여부 확인
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        articleForm.setTitle(article.getTitle());
        articleForm.setBody(article.getBody());
        articleForm.setIsPaid(article.getIsPaid());
        return "domain/home/home/article/article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/modify")
    public String articleModify(@Valid ArticleForm articleForm, BindingResult bindingResult, @PathVariable("id") Long id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "domain/home/home/article/article_form";
        }
        Article article = this.articleService.getArticle(id);
        if (!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.articleService.modify(article, articleForm.getTitle(), articleForm.getBody(), articleForm.getIsPaid());
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
