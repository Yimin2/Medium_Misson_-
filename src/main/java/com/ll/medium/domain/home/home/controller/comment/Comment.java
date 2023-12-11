package com.ll.medium.domain.home.home.controller.comment;

import com.ll.medium.domain.home.home.controller.article.Article;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private LocalDateTime createDate;

    @ManyToOne
    private Article article; // 질문 엔티티를 참조하기 위해
}
