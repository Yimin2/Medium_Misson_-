package com.ll.medium.domain.home.home.controller.article;

import com.ll.medium.domain.home.home.controller.comment.Comment;
import com.ll.medium.domain.home.home.controller.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    // cascade = CascadeType.REMOVE 질문이 삭제되면 답변도 삭제
    private List<Comment> commentList;

    @ManyToOne
    private Member author;

    private LocalDateTime modifyDate;

    private Boolean isPaid;
}
