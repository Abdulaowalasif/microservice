package com.quiz.controllers;

import com.quiz.entities.Quiz;
import com.quiz.service.QuizService;
import com.quiz.service.implementation.QuizServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<Quiz> add(@RequestBody Quiz quiz) {
        return new ResponseEntity<>(quizService.addQuiz(quiz), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuiz() {
        return ResponseEntity.ok(quizService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getOneQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.get(id));
    }


}
