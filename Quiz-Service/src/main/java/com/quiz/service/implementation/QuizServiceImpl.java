package com.quiz.service.implementation;

import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.service.QuestionClients;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizRepository repository;
    private QuestionClients questionClients;

    public QuizServiceImpl(QuizRepository repository, QuestionClients questionClients) {
        this.repository = repository;
        this.questionClients = questionClients;
    }

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return repository.save(quiz);
    }

    @Override
    public List<Quiz> get() {
        List<Quiz> quizzes = repository.findAll();

        return quizzes.stream()
                .map(quiz -> {
                    quiz.setQuestions(questionClients.getQuestionOfQuiz(quiz.getId()));
                    return quiz;
                })
                .collect(Collectors.toList());
    }


    @Override
    public Quiz get(Long id) {
        Quiz quiz = repository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found."));
        quiz.setQuestions(questionClients.getQuestionOfQuiz(quiz.getId()));
        return quiz;
    }
}
