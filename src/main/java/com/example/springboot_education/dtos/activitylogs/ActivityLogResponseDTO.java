package com.example.springboot_education.dtos.activitylogs;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ActivityLogResponseDTO {
    private Integer id;
    private Integer userId;
    private String actionType;
    private String targetTable;
    private Integer targetId;
    private String description;
    private Timestamp createdAt;
}

