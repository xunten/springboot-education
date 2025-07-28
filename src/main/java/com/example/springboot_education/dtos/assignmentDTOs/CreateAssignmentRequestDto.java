package com.example.springboot_education.dtos.assignmentDTOs;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAssignmentRequestDto {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Class ID is required")
    private Long class_id;

    @NotNull(message = "Due date is required")
    private Date due_date;

    @NotNull(message = "Max score is required")
    @Positive(message = "Max score must be positive")
    private double max_score;
}
