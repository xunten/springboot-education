package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.finalgrades.*;
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
    public List<FinalGradeResponseDTO> getGradesByClass(@PathVariable(name = "classId") Long classId) {
        return finalGradeService.getFinalGradesByClass(classId);
    }

@GetMapping("/student/{studentId}")
public List<FinalGradeResponseDTO> getGradesByStudent(@PathVariable(name = "studentId") Long studentId) {
    return finalGradeService.getFinalGradesByStudent(studentId);
}


    @GetMapping("/class/{classId}/semester/{semester}")
    public List<FinalGradeResponseDTO> getGradesByClassAndSemester(@PathVariable(name = "classId") Long classId, @PathVariable(name = "semester") String semester) {
        return finalGradeService.getFinalGradesByClassAndSemester(classId, semester);
    }

    @PostMapping
    public FinalGradeResponseDTO createFinalGrade(@RequestBody FinalGradeCreateDTO createDTO) {
        return finalGradeService.createFinalGrade(createDTO);
    }

    @PatchMapping("/{id}")
    public FinalGradeResponseDTO updateFinalGrade(@PathVariable(name = "id") Long id, @RequestBody FinalGradeUpdateDTO updateDTO) {
        return finalGradeService.updateFinalGrade(id, updateDTO);
    }
    
}
