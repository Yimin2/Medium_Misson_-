package com.ll.medium.domain.home.home.controller.answer;

import com.ll.medium.domain.home.home.controller.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private LocalDateTime createDate;

    @ManyToOne
    private Question question; // 질문 엔티티를 참조하기 위해
}
