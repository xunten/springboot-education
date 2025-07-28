package com.example.springboot_education.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springboot_education.dtos.assignmentDTOs.AssignmentResponseDto;
import com.example.springboot_education.entities.Assignment;
import com.example.springboot_education.repositories.AssignmentJpaRepository;

@Service
public class AssignmentService {
    private AssignmentJpaRepository assignmentRepository;

    public AssignmentService(AssignmentJpaRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    private AssignmentResponseDto convertToDto(Assignment assignment) {
        return new AssignmentResponseDto(
            assignment.getId(),
            assignment.getTitle(),
            assignment.getDescription(),
            assignment.getDue_date(),
            assignment.getMax_score()
        );
    }

    public List<AssignmentResponseDto> getAllAssignments() {
        List<Assignment> assignments = assignmentRepository.findAll();
        return assignments.stream().map(this::convertToDto).toList();
    }
}
