package com.ll.medium.domain.home.home.controller.question;

import com.ll.medium.domain.home.home.controller.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "domain/home/home/question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "domain/home/home/question_detail";
    }

    @GetMapping("/write")
    public String questionWrite(QuestionForm questionForm) {
        return "domain/home/home/question_form";
    }

    @PostMapping("/write")
    public String questionWrite(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "domain/home/home/question_form";
        }
        this.questionService.write(questionForm.getTitle(), questionForm.getBody());
        return "redirect:/post/list";
    }
}
