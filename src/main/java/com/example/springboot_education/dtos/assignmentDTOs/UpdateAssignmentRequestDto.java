package com.example.springboot_education.dtos.assignmentDTOs;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAssignmentRequestDto {
    private String title;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date due_date;

    private double max_score;

    private Long class_id;
}
