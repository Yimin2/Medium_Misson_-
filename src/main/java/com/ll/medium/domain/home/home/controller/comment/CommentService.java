package com.ll.medium.domain.home.home.controller.comment;

import com.ll.medium.domain.home.home.controller.article.Article;
import com.ll.medium.domain.home.home.controller.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public void write(Article article, String body, Member author) {
        Comment comment = new Comment();
        comment.setBody(body);
        comment.setCreateDate(LocalDateTime.now());
        comment.setArticle(article);
        comment.setAuthor(author);
        this.commentRepository.save(comment);
    }
}
