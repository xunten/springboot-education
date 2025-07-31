package com.example.springboot_education.dtos.classUserDTOs;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassUserResponseDto {
    private Long classId;
    private Long studentId;
    private Timestamp joinedAt;
}
