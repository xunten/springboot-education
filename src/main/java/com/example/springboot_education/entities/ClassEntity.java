package com.example.springboot_education.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "classes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {
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

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToMany(mappedBy = "clazz")
    private List<ClassMember> members;

    @OneToMany(mappedBy = "clazz")
    private List<ClassActivity> activities;
}