package com.example.springboot_education.dtos.assignmentDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
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
public class UpdateAssignmentRequestDto {

    private Long classId;
    private String title;
    private String description;
    private Date dueDate;
    private BigDecimal maxScore;
    private String filePath;
    private String fileType;
}

