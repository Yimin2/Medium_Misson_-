package com.ll.medium.domain.home.home.controller.article;

import com.ll.medium.domain.home.home.controller.comment.CommentForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class ArticleController {
    private final ArticleService articleService;

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

    @GetMapping("/write")
    public String articleWrite(ArticleForm articleForm) {
        return "domain/home/home/article/article_form";
    }

    @PostMapping("/write")
    public String articleWrite(@Valid ArticleForm articleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "domain/home/home/aritcle/article_form";
        }
        this.articleService.write(articleForm.getTitle(), articleForm.getBody());
        return "redirect:/post/list";
    }
}
