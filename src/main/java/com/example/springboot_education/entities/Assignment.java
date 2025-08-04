package com.example.springboot_education.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long classId;

    private String title;

    @Column(length = 1000)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    private BigDecimal maxScore;

    private String filePath;

    private String fileType;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}