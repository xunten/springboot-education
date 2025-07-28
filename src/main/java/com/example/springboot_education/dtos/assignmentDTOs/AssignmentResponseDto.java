package com.example.springboot_education.dtos.assignmentDTOs;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AssignmentResponseDto {
    private Long id;
    private String title;
    private String description;
    private Date due_date;
    private double max_score;
    private Long class_id;
    private Date create_at;
    private Date updated_at;

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
