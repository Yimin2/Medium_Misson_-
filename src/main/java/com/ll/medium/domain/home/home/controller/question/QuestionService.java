package com.ll.medium.domain.home.home.controller.question;

import com.ll.medium.domain.home.home.controller.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Long id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public  void write(String title, String body) {
        Question q = new Question();
        q.setTitle(title);
        q.setBody(body);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
}
