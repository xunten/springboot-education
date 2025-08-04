package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.submissionDTOs.GradeSubmissionRequestDto;
import com.example.springboot_education.dtos.submissionDTOs.SubmissionRequestDto;
import com.example.springboot_education.dtos.submissionDTOs.SubmissionResponseDto;
import com.example.springboot_education.entities.Submission;
import com.example.springboot_education.services.SubmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {
    private final SubmissionService submissionService;

    @GetMapping
    public List<SubmissionResponseDto> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    // Upload và nộp bài
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SubmissionResponseDto> submitAssignment(
            @RequestParam("assignmentId") Long assignmentId,
            @RequestParam("studentId") Long studentId,
            @RequestPart("file") MultipartFile file) throws IOException {

        String filePath = submissionService.storeFile(file); // lưu file thực tế

        SubmissionRequestDto requestDto = new SubmissionRequestDto();
        requestDto.setAssignmentId(assignmentId);
        requestDto.setStudentId(studentId);
        requestDto.setFilePath(filePath);
        requestDto.setFileType(file.getContentType());

        SubmissionResponseDto response = submissionService.submitAssignment(requestDto);
        return ResponseEntity.ok(response);
    }

    // Chấm điểm
    @PatchMapping("/{submissionId}/grade")
    public ResponseEntity<SubmissionResponseDto> gradeSubmission(
            @PathVariable Long submissionId,
            @RequestBody @Valid GradeSubmissionRequestDto dto) {
        SubmissionResponseDto response = submissionService.gradeSubmission(
                submissionId, dto.getScore(), dto.getComment());
        return ResponseEntity.ok(response);
    }

    // Lấy bài nộp theo assignment
    @GetMapping("/assignment/{assignmentId}")
    public List<SubmissionResponseDto> getByAssignment(@PathVariable Long assignmentId) {
        return submissionService.getSubmissionsByAssignment(assignmentId);
    }

    // Lấy bài nộp theo học sinh
    @GetMapping("/student/{studentId}")
    public List<SubmissionResponseDto> getByStudent(@PathVariable Long studentId) {
        return submissionService.getSubmissionsByStudent(studentId);
    }

    // Lấy bài nộp duy nhất của học sinh cho 1 assignment
    @GetMapping("/assignment/{assignmentId}/student/{studentId}")
    public SubmissionResponseDto getByAssignmentAndStudent(
            @PathVariable Long assignmentId,
            @PathVariable Long studentId) {
        return submissionService.getSubmission(assignmentId, studentId);
    }

    // Tải file nộp bài về
    @GetMapping("/{submissionId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long submissionId) throws IOException {
        Submission submission = submissionService.getSubmissionEntityById(submissionId);

        Path filePath = Paths.get(submission.getFilePath());
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("File not found");
        }

        Resource fileResource = new UrlResource(filePath.toUri());
        String fileName = filePath.getFileName().toString();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(fileResource);
    }

    // Xóa file nộp bài
    @DeleteMapping("/{submissionId}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long submissionId) {
        submissionService.deleteSubmission(submissionId);
        return ResponseEntity.noContent().build();
    }


}



