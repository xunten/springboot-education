package com.example.springboot_education.dtos;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ClassResponseDTO {
    private Integer id;
    private String className;
    private String subject;
    private Integer schoolYear;
    private String semester;
    private String description;
    private Timestamp createdAt;
    private Integer teacherId;
}
