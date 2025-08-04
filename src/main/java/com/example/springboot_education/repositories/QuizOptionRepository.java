package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.QuizOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizOptionRepository extends JpaRepository<QuizOption, Integer> {
    List<QuizOption> findByQuestion_Id(Integer questionId);
}
