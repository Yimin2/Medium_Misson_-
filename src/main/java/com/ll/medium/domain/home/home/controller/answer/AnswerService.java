package com.ll.medium.domain.home.home.controller.answer;

import com.ll.medium.domain.home.home.controller.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void write(Question question, String body) {
        Answer answer = new Answer();
        answer.setBody(body);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        this.answerRepository.save(answer);
    }
}
