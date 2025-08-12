package com.question.controllers;

import com.question.entitirs.Question;
import com.question.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Question> add(@RequestBody Question question) {
        return ResponseEntity.ok(service.add(question));
    }

    @GetMapping
    public ResponseEntity<List<Question>> get() {
        return new ResponseEntity<>(service.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsOfQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(service.getQuestionsOfQuiz(quizId));
    }

}
