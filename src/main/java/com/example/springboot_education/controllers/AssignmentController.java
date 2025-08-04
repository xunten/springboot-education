package com.example.springboot_education.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import com.example.springboot_education.entities.Assignment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springboot_education.dtos.assignmentDTOs.AssignmentResponseDto;
import com.example.springboot_education.dtos.assignmentDTOs.CreateAssignmentRequestDto;
import com.example.springboot_education.dtos.assignmentDTOs.UpdateAssignmentRequestDto;
import com.example.springboot_education.services.AssignmentService;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping()
    public List<AssignmentResponseDto> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    @GetMapping("/{id}")
    public AssignmentResponseDto getAssignmentById(@PathVariable("id") Long id) {
        return assignmentService.getAssignmentById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AssignmentResponseDto> createAssignment(
            @RequestParam Long classId,
            @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dueDate,
            @RequestParam BigDecimal maxScore,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        CreateAssignmentRequestDto dto = new CreateAssignmentRequestDto();
        dto.setClassId(classId);
        dto.setTitle(title);
        dto.setDescription(description);
        dto.setDueDate(dueDate);
        dto.setMaxScore(maxScore);

        return ResponseEntity.ok(assignmentService.createAssignmentWithFile(dto, file));
    }

    @PatchMapping("/{id}")
    public AssignmentResponseDto updateAssignment(@PathVariable("id") Long id, @RequestBody @Valid UpdateAssignmentRequestDto updateAssignmentRequestDto) {
        return assignmentService.updateAssignment(id, updateAssignmentRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAssignment(@PathVariable("id") Long id) {
        assignmentService.deleteAssignment(id);
    }

    @GetMapping("/{assignmentId}/download")
    public ResponseEntity<Resource> downloadAssignmentFile(@PathVariable Long assignmentId) throws IOException {
        Assignment assignment = assignmentService.getAssignmentEntityById(assignmentId);

        Path filePath = Paths.get(assignment.getFilePath());
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("File not found");
        }

        Resource resource = new UrlResource(filePath.toUri());
        String fileName = filePath.getFileName().toString();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

}
