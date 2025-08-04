package com.example.springboot_education.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "submissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {

    public enum SubmissionStatus {
        SUBMITTED,
        GRADED,
        LATE,
        MISSING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Users student;  // hoặc Student entity tùy vào hệ thống

    private String filePath;

    private String fileType;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    private BigDecimal score;

    @Column(length = 1000)
    private String teacherComment;

    private Timestamp submittedAt;

    private Timestamp gradedAt;

    @PrePersist
    protected void onSubmit() {
        submittedAt = new Timestamp(System.currentTimeMillis());
    }

    public boolean isGraded() {
        return score != null;
    }

    public boolean isLate() {
        if (assignment == null || assignment.getDueDate() == null || submittedAt == null) return false;
        return submittedAt.after(assignment.getDueDate());
    }

}
