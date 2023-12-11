package com.ll.medium.domain.home.home.controller.answer;

import com.ll.medium.domain.home.home.controller.question.Question;
import com.ll.medium.domain.home.home.controller.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer/")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/write/{id}")
    public String writeAnswer(@PathVariable("id") Long id, @RequestParam(value="content") String body) {
        Question question = this.questionService.getQuestion(id);
        this.answerService.write(question, body);
        return String.format("redirect:/post/detail/%s", id);
    }
}