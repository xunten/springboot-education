package com.example.springboot_education.dtos.submissionDTOs;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class SubmissionRequestDto {
    private Long assignmentId;
    private Long studentId;
    private String filePath;
    private String fileType;
}
