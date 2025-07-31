package com.example.springboot_education.dtos;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ClassActivityDTO {
    private Integer id;
    private String activityType;
    private Integer targetId;
    private String description;
    private Timestamp createdAt;
    private Integer classId;
    private Integer userId;
}

