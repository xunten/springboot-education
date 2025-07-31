// CreateClassActivityDTO.java
package com.example.springboot_education.dtos;

import lombok.Data;

@Data
public class CreateClassActivityDTO {
    private String activityType;
    private Integer targetId;
    private String description;
    private Integer classId;
    private Integer userId;
}