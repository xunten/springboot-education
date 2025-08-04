package com.example.springboot_education.dtos.activitylogs;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ActivityLogResponseDTO {
    private Long id;
    private Long userId;
    private String actionType;
    private String targetTable;
    private Integer targetId;
    private String description;
    private Timestamp createdAt;
}

