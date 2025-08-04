package com.example.springboot_education.services;

import com.example.springboot_education.dtos.submissionDTOs.SubmissionRequestDto;
import com.example.springboot_education.dtos.submissionDTOs.SubmissionResponseDto;
import com.example.springboot_education.entities.Assignment;
import com.example.springboot_education.entities.Submission;
import com.example.springboot_education.entities.Users;
import com.example.springboot_education.repositories.AssignmentJpaRepository;
import com.example.springboot_education.repositories.SubmissionJpaRepository;
import com.example.springboot_education.repositories.UsersJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionJpaRepository submissionJpaRepository;
    private final AssignmentJpaRepository assignmentJpaRepository;
    private final UsersJpaRepository usersJpaRepository;

    private SubmissionResponseDto convertToDto(Submission submission) {
        SubmissionResponseDto dto = new SubmissionResponseDto();
        dto.setId(submission.getId());
        dto.setAssignmentId(submission.getAssignment().getId());
        dto.setStudentId(submission.getStudent().getId());
        dto.setFilePath(submission.getFilePath());
        dto.setFileType(submission.getFileType());
        dto.setStatus(submission.getStatus());
        dto.setScore(submission.getScore());
        dto.setTeacherComment(submission.getTeacherComment());
        dto.setSubmittedAt(submission.getSubmittedAt());
        dto.setGradedAt(submission.getGradedAt());
        return dto;
    }

    public List<SubmissionResponseDto> getAllSubmissions() {
        List<Submission> submissions = submissionJpaRepository.findAll();
        return submissions.stream().map(this::convertToDto).toList();
    }

    public SubmissionResponseDto submitAssignment(SubmissionRequestDto requestDto) {
        Assignment assignment = assignmentJpaRepository.findById(requestDto.getAssignmentId())
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));

        // Kiểm tra hạn nộp
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (assignment.getDueDate() != null && now.after(assignment.getDueDate())) {
            throw new IllegalStateException("Submission deadline has passed");
        }

        Users student = usersJpaRepository.findById(requestDto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        Submission submission = new Submission();
        submission.setAssignment(assignment);
        submission.setStudent(student);
        submission.setFilePath(requestDto.getFilePath());
        submission.setFileType(requestDto.getFileType());
        submission.setStatus(Submission.SubmissionStatus.SUBMITTED);

        Submission saved = submissionJpaRepository.save(submission);
        return convertToDto(saved);
    }

    public SubmissionResponseDto gradeSubmission(Long submissionId, BigDecimal score, String comment) {
        Submission submission = submissionJpaRepository.findById(submissionId)
                .orElseThrow(() -> new EntityNotFoundException("Submission not found"));

        submission.setScore(score);
        submission.setTeacherComment(comment);
        submission.setStatus(Submission.SubmissionStatus.GRADED);
        submission.setGradedAt(new Timestamp(System.currentTimeMillis()));

        Submission graded = submissionJpaRepository.save(submission);
        return convertToDto(graded);
    }

    public List<SubmissionResponseDto> getSubmissionsByAssignment(Long assignmentId) {
        return submissionJpaRepository.findByAssignmentId(assignmentId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<SubmissionResponseDto> getSubmissionsByStudent(Long studentId) {
        return submissionJpaRepository.findByStudentId(studentId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public SubmissionResponseDto getSubmission(Long assignmentId, Long studentId) {
        Submission submission = submissionJpaRepository.findByAssignmentIdAndStudentId(assignmentId, studentId)
                .orElseThrow(() -> new EntityNotFoundException("Submission not found"));
        return convertToDto(submission);
    }


    public String storeFile(MultipartFile file) throws IOException {
        String uploadDir = System.getProperty("user.dir") + "/uploads/assignments";
        Files.createDirectories(Paths.get(uploadDir)); // tạo thư mục nếu chưa có

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, filename);
        Files.write(filePath, file.getBytes());

        return filePath.toString(); // lưu đường dẫn vào DB
    }

    // tải file nộp về
    public Submission getSubmissionEntityById(Long id) {
        return submissionJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Submission not found with id: " + id));
    }

    // xóa file nộp
    public void deleteSubmission(Long submissionId) {
        Submission submission = submissionJpaRepository.findById(submissionId)
                .orElseThrow(() -> new EntityNotFoundException("Submission not found"));

        // Xóa file vật lý nếu tồn tại
        try {
            Path filePath = Paths.get(submission.getFilePath());
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file", e);
        }

        // Xóa dữ liệu trong DB
        submissionJpaRepository.delete(submission);
    }



}
