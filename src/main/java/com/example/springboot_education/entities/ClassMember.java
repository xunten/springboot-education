package com.example.springboot_education.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "class_members")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ClassMemberId.class)
public class ClassMember {
    @Id
    @Column(name = "class_id")
    private Integer classId;

    @Id
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "joined_at")
    private Timestamp joinedAt;

    @ManyToOne
    @JoinColumn(name = "class_id", insertable = false, updatable = false)
    private ClassEntity clazz;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private User student;
}
