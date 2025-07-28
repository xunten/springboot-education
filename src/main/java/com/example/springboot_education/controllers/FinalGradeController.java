package com.example.springboot_education.controllers;

import com.example.springboot_education.entities.FinalGrade;
import com.example.springboot_education.services.FinalGradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/final-grades")
public class FinalGradeController {

    private final FinalGradeService finalGradeService;

    public FinalGradeController(FinalGradeService finalGradeService) {
        this.finalGradeService = finalGradeService;
    }

    @GetMapping("/class/{classId}")
    public List<FinalGrade> getGradesByClass(@PathVariable Long classId) {
        return finalGradeService.getFinalGradesByClass(classId);
    }

    @GetMapping("/student/{studentId}")
    public List<FinalGrade> getGradesByStudent(@PathVariable Long studentId) {
        return finalGradeService.getFinalGradesByStudent(studentId);
    }

    @GetMapping("/class/{classId}/semester/{semester}")
    public List<FinalGrade> getGradesByClassAndSemester(@PathVariable Long classId, @PathVariable String semester) {
        return finalGradeService.getFinalGradesByClassAndSemester(classId, semester);
    }
}
