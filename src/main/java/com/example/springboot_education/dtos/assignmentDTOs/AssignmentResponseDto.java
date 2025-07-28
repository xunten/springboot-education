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

    public AssignmentResponseDto(Long id, String title, String description, Date due_date, double max_score) {
       this.id = id;
       this.title = title;
       this.description = description;
       this.due_date = due_date;
       this.max_score = max_score;
    }
}
