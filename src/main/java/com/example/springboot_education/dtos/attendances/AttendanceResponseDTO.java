package com.example.springboot_education.dtos.attendances;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AttendanceResponseDTO {
    private Integer id;
    private Long studentId;
    private Integer scheduleId;
    private Timestamp markedAt;
    private String status;
}
