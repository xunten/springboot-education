package com.example.springboot_education.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.springboot_education.dtos.finalgrades.FinalGradeCreateDTO;
import com.example.springboot_education.dtos.finalgrades.FinalGradeResponseDTO;
import com.example.springboot_education.dtos.finalgrades.FinalGradeUpdateDTO;
import com.example.springboot_education.entities.FinalGrade;
import com.example.springboot_education.repositories.FinalGradeRepository;

@Service
public class FinalGradeService {

    private final FinalGradeRepository finalGradeRepository;

    public FinalGradeService(FinalGradeRepository finalGradeRepository) {
        this.finalGradeRepository = finalGradeRepository;
    }

    // Convert Entity -> ResponseDTO
    private FinalGradeResponseDTO mapToDTO(FinalGrade finalGrade) {
        FinalGradeResponseDTO dto = new FinalGradeResponseDTO();
        dto.setId(finalGrade.getId());
        dto.setStudentId(finalGrade.getStudentId());
        dto.setClassId(finalGrade.getClassId());
        dto.setSemester(finalGrade.getSemester());
        dto.setSchoolYear(finalGrade.getSchoolYear());
        dto.setAverageScore(finalGrade.getAverageScore());
        dto.setLetterGrade(finalGrade.getLetterGrade());
        return dto;
    }

    // GET Methods
    public List<FinalGradeResponseDTO> getFinalGradesByClass(Long classId) {
        return finalGradeRepository.findByClassId(classId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<FinalGradeResponseDTO> getFinalGradesByStudent(Long studentId) {
        return finalGradeRepository.findByStudentId(studentId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<FinalGradeResponseDTO> getFinalGradesByClassAndSemester(Long classId, String semester) {
        return finalGradeRepository.findByClassIdAndSemester(classId, semester)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // POST - Create
    public FinalGradeResponseDTO createFinalGrade(FinalGradeCreateDTO createDTO) {
        FinalGrade finalGrade = new FinalGrade();
        finalGrade.setStudentId(createDTO.getStudentId());
        finalGrade.setClassId(createDTO.getClassId());
        finalGrade.setSemester(createDTO.getSemester());
        finalGrade.setSchoolYear(createDTO.getSchoolYear());
        finalGrade.setAverageScore(createDTO.getAverageScore());
        finalGrade.setLetterGrade(createDTO.getLetterGrade());
        FinalGrade saved = finalGradeRepository.save(finalGrade);
        return mapToDTO(saved);
    }

    // PATCH - Update partial fields
    public FinalGradeResponseDTO updateFinalGrade(Long id, FinalGradeUpdateDTO updateDTO) {
        FinalGrade finalGrade = finalGradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FinalGrade not found"));

        if (updateDTO.getAverageScore() != null) {
            finalGrade.setAverageScore(updateDTO.getAverageScore());
        }

        if (updateDTO.getLetterGrade() != null) {
            finalGrade.setLetterGrade(updateDTO.getLetterGrade());
        }

        FinalGrade updated = finalGradeRepository.save(finalGrade);
        return mapToDTO(updated);
    }
}
