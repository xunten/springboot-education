package com.example.springboot_education.dtos.assignmentDTOs;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAssignmentRequestDto {

    private Long classId;
    private String title;
    private String description;
    private Date dueDate;
    private BigDecimal maxScore;
    private String filePath;
    private String fileType;
}
