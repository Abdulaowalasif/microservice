package com.question.service.impl;

import com.question.entitirs.Question;
import com.question.repositories.QuestionRepository;
import com.question.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(Question question) {
        return repository.save(question);
    }

    @Override
    public List<Question> get() {
        return repository.findAll();
    }

    @Override
    public Question getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Question not found."));
    }

    @Override
    public List<Question> getQuestionsOfQuiz(Long quizId) {
        return repository.findByQuizId(quizId);
    }
}
