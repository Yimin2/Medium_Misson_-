package com.ll.medium.domain.home.home.controller.comment;

import com.ll.medium.domain.home.home.controller.article.Article;
import com.ll.medium.domain.home.home.controller.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/post/")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @PostMapping("/{id}/comment/write")
    public String writeAnswer(@PathVariable("id") Long id, @RequestParam(value="content") String body) {
        Article article = this.articleService.getArticle(id);
        this.commentService.write(article, body);
        return String.format("redirect:/post/detail/%s", id);
    }
}