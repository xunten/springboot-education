package com.example.springboot_education.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class CreateClassActivityRequest {
    @NotNull
    private Integer classId;

    @NotNull
    private Integer userId;

    @NotBlank
    private String activityType;

    private Integer targetId;

    @NotBlank
    private String description;
}
