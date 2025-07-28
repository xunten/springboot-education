package com.example.springboot_education.entities;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class_members")

public class ClassMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long class_id;
    private Long student_id;
    private Timestamp joined_at;
    }


