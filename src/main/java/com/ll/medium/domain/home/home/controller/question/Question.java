package com.ll.medium.domain.home.home.controller.question;

import com.ll.medium.domain.home.home.controller.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    // cascade = CascadeType.REMOVE 질문이 삭제되면 답변도 삭제
    private List<Answer> answerList;
}
