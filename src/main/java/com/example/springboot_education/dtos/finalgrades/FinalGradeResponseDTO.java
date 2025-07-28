package com.example.springboot_education.dtos.finalgrades;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FinalGradeResponseDTO {
    private Long id;
    private Long studentId;
    private Long classId;
    private String semester;
    private Integer schoolYear;
    private BigDecimal averageScore;
    private String letterGrade;
}
