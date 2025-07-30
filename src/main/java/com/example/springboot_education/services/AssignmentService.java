package com.example.springboot_education.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springboot_education.dtos.assignmentDTOs.AssignmentResponseDto;
import com.example.springboot_education.dtos.assignmentDTOs.CreateAssignmentRequestDto;
import com.example.springboot_education.dtos.assignmentDTOs.UpdateAssignmentRequestDto;
import com.example.springboot_education.repositories.AssignmentJpaRepository;

@Service
public class AssignmentService {
    private AssignmentJpaRepository assignmentJpaRepository;

    public AssignmentService(AssignmentJpaRepository assignmentJpaRepository) {
        this.assignmentJpaRepository = assignmentJpaRepository;
    }

    private AssignmentResponseDto convertToDto(Assignment assignment) {
        return new AssignmentResponseDto(
                assignment.getId(),
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getDue_date(),
                assignment.getMax_score(),
                assignment.getClass_id(),
                assignment.getCreated_at(),
                assignment.getUpdated_at()
        );
    }

    public List<AssignmentResponseDto> getAllAssignments() {
        List<Assignment> assignments = assignmentJpaRepository.findAll();
        return assignments.stream().map(this::convertToDto).toList();
    }

    public AssignmentResponseDto getAssignmentById(Long id) {
        Assignment assignment = assignmentJpaRepository.findById(id).orElseThrow();
        return convertToDto(assignment);
    }

    public AssignmentResponseDto createAssignment(CreateAssignmentRequestDto createAssignmentRequestDto) {

        Assignment assignment = new Assignment();
        assignment.setTitle(createAssignmentRequestDto.getTitle());
        assignment.setDescription(createAssignmentRequestDto.getDescription());
        assignment.setDue_date(createAssignmentRequestDto.getDue_date());
        assignment.setMax_score(createAssignmentRequestDto.getMax_score());
        assignment.setClass_id(createAssignmentRequestDto.getClass_id());
        assignment.setCreated_at(new Timestamp(System.currentTimeMillis()));

        Assignment savedAssignment = assignmentJpaRepository.save(assignment);
        return convertToDto(savedAssignment);
    }

    public AssignmentResponseDto updateAssignment(Long id, UpdateAssignmentRequestDto updateAssignmentRequestDto) {
        Assignment assignment = assignmentJpaRepository.findById(id).orElseThrow();

        assignment.setTitle(updateAssignmentRequestDto.getTitle());
        assignment.setDescription(updateAssignmentRequestDto.getDescription());
        assignment.setDue_date(updateAssignmentRequestDto.getDue_date());
        assignment.setMax_score(updateAssignmentRequestDto.getMax_score());
        assignment.setClass_id(updateAssignmentRequestDto.getClass_id());
        assignment.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        Assignment updatedAssignment = assignmentJpaRepository.save(assignment);
        return convertToDto(updatedAssignment);
    }

    public void deleteAssignment(Long id) {
        Assignment assignment = assignmentJpaRepository.findById(id).orElseThrow();
        assignmentJpaRepository.delete(assignment);
    }
}
