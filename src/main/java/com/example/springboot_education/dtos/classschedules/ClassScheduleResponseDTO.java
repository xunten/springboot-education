package com.example.springboot_education.dtos.classschedules;

import java.sql.Time;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class ClassScheduleResponseDTO {
    private Integer id;
    private Integer classId;
    private Integer dayOfWeek;
    private Time startTime;
    private Time endTime;
    private String location;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

