package com.example.springboot_education.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "classes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_name")
    private String className;

    private String subject;

    @Column(name = "school_year")
    private Integer schoolYear;

    private String semester;

    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    
    

}