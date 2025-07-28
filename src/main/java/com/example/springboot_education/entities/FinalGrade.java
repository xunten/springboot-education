package com.example.springboot_education.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "final_grades")
@Data
public class FinalGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long classId;
    private String semester;
    private Double averageScore;
}
