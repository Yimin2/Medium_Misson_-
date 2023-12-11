package com.ll.medium.domain.home.home.controller.comment;

import com.ll.medium.domain.home.home.controller.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public void write(Article article, String body) {
        Comment answer = new Comment();
        answer.setBody(body);
        answer.setCreateDate(LocalDateTime.now());
        answer.setArticle(article);
        this.commentRepository.save(answer);
    }
}
