package com.example.springboot_education.dtos.assignmentDTOs;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AssignmentResponseDto {
    private Integer id;
    private String title;
    private String description;
    private Integer classId;
    private Instant dueDate;
    private BigDecimal maxScore;
    private Instant createdAt;
    private Instant updatedAt;
    private String filePath;
    private String fileType;

    public AssignmentResponseDto(Long id, String title, String description, Date due_date, double max_score, Long class_id, Date create_at, Date updated_at) {
       this.id = id;
       this.title = title;
       this.description = description;
       this.due_date = due_date;
       this.max_score = max_score;
       this.class_id = class_id;
       this.create_at = create_at;
       this.updated_at = updated_at;
    }
}
