package com.example.springboot_education.dtos.assignmentDTOs;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AssignmentResponseDto {
    private Long id;
    private String title;
    private String description;
    private Long classId;
    private Date dueDate;
    private BigDecimal maxScore;
    private String filePath;
    private String fileType;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
