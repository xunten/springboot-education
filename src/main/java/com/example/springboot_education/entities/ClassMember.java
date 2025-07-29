package com.example.springboot_education.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "ClassMember")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassMember {

    @EmbeddedId
    private ClassMemberId id;

    @ManyToOne
    @MapsId("class_id")
    @JoinColumn(name = "class_id")
    private Class aClass;  

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    private Users student;

    @Column(name = "joined_at")
    private Timestamp joinedAt;
}
