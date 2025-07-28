package com.example.springboot_education.services;

import com.example.springboot_education.entities.FinalGrade;
import com.example.springboot_education.repositories.FinalGradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalGradeService {
    private final FinalGradeRepository finalGradeRepository;

    public FinalGradeService(FinalGradeRepository finalGradeRepository) {
        this.finalGradeRepository = finalGradeRepository;
    }

    public List<FinalGrade> getFinalGradesByClass(Long classId) {
        return finalGradeRepository.findByClassId(classId);
    }

    public List<FinalGrade> getFinalGradesByStudent(Long studentId) {
        return finalGradeRepository.findByStudentId(studentId);
    }

    public List<FinalGrade> getFinalGradesByClassAndSemester(Long classId, String semester) {
        return finalGradeRepository.findByClassIdAndSemester(classId, semester);
    }
}
