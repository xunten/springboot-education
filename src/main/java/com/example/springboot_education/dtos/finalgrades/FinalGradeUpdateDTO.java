package com.example.springboot_education.dtos.finalgrades;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FinalGradeUpdateDTO {
    private BigDecimal averageScore;
    private String letterGrade;
}
