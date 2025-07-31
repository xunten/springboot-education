package com.example.springboot_education.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateClassRequest {
    @NotBlank
    private String className;

    @NotBlank
    private String subject;

    @NotNull
    private Integer schoolYear;

    @NotBlank
    private String semester;

    private String description;
}


