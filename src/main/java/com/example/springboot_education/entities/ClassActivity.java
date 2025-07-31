package com.example.springboot_education.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "class_activities")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "target_id")
    private Integer targetId;

    private String description;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity clazz;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

