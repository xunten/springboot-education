package com.example.springboot_education.services.quiz;


import com.example.springboot_education.dtos.quiz.QuizRequestDTO;
import com.example.springboot_education.dtos.quiz.QuizResponseDTO;
import com.example.springboot_education.dtos.quiz.QuizSubmitReqDTO;
import com.example.springboot_education.dtos.quiz.QuizSubmitResDTO;

import java.util.List;

public interface QuizService {
    QuizResponseDTO createQuiz(QuizRequestDTO quizDTO);
    QuizResponseDTO getQuizById(Integer id);
    List<QuizResponseDTO> getAllQuizzes();
    QuizSubmitResDTO submitQuiz(QuizSubmitReqDTO request);
}
