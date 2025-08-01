package com.example.springboot_education.dtos.classschedules;

import java.sql.Time;

import lombok.Data;

@Data
public class ClassScheduleCreateDTO {
    private Integer classId;
    private Integer dayOfWeek;
    private Time startTime;
    private Time endTime;
    private String location;
}
