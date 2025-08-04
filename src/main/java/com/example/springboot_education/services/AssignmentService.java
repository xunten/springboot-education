package com.example.springboot_education.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.springboot_education.dtos.assignmentDTOs.AssignmentResponseDto;
import com.example.springboot_education.dtos.assignmentDTOs.CreateAssignmentRequestDto;
import com.example.springboot_education.dtos.assignmentDTOs.UpdateAssignmentRequestDto;
import com.example.springboot_education.entities.Assignment;
import com.example.springboot_education.repositories.AssignmentJpaRepository;

import jakarta.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class AssignmentService {
    private final AssignmentJpaRepository assignmentJpaRepository;

    private AssignmentResponseDto convertToDto(Assignment assignment) {
        AssignmentResponseDto assignmentResponseDto = new AssignmentResponseDto();

        assignmentResponseDto.setId(assignment.getId());
        assignmentResponseDto.setClassId(assignment.getClassId());
        assignmentResponseDto.setTitle(assignment.getTitle());
        assignmentResponseDto.setDescription(assignment.getDescription());
        assignmentResponseDto.setDueDate(assignment.getDueDate());
        assignmentResponseDto.setMaxScore(assignment.getMaxScore());
        assignmentResponseDto.setFilePath(assignment.getFilePath());
        assignmentResponseDto.setFileType(assignment.getFileType());
        assignmentResponseDto.setCreatedAt(assignment.getCreatedAt());
        assignmentResponseDto.setUpdatedAt(assignment.getUpdatedAt());

        return assignmentResponseDto;
    }

    public List<AssignmentResponseDto> getAllAssignments() {
        List<Assignment> assignments = assignmentJpaRepository.findAll();
        return assignments.stream().map(this::convertToDto).toList();
    }

    public AssignmentResponseDto getAssignmentById(Long id) {
        Assignment assignment = assignmentJpaRepository.findById(id).orElseThrow();
        return convertToDto(assignment);
    }

    public AssignmentResponseDto createAssignment(CreateAssignmentRequestDto dto) {
        Assignment assignment = new Assignment();

        assignment.setTitle(dto.getTitle());
        assignment.setClassId(dto.getClassId());
        assignment.setDescription(dto.getDescription());
        assignment.setDueDate(dto.getDueDate());
        assignment.setMaxScore(dto.getMaxScore());
        assignment.setFilePath(dto.getFilePath());
        assignment.setFileType(dto.getFileType());

        Assignment savedAssignment = assignmentJpaRepository.save(assignment);
        return convertToDto(savedAssignment);
    }

    public AssignmentResponseDto updateAssignment(Long id, UpdateAssignmentRequestDto dto) {
        Assignment assignment = assignmentJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found with id: " + id));

        assignment.setTitle(dto.getTitle());
        assignment.setClassId(dto.getClassId());
        assignment.setDescription(dto.getDescription());
        assignment.setDueDate(dto.getDueDate());
        assignment.setMaxScore(dto.getMaxScore());
        assignment.setFilePath(dto.getFilePath());
        assignment.setFileType(dto.getFileType());


        Assignment updatedAssignment = assignmentJpaRepository.save(assignment);
        return convertToDto(updatedAssignment);
    }

    public void deleteAssignment(Long id) {
        Assignment assignment = assignmentJpaRepository.findById(id).orElseThrow();
        assignmentJpaRepository.delete(assignment);
    }
}
