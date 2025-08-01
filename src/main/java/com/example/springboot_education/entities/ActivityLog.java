package com.example.springboot_education.entities;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activity_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "target_table")
    private String targetTable;

    @Column(name = "target_id")
    private Integer targetId;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;
}
