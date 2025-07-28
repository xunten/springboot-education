package com.example.springboot_education.entities;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date due_date;
    private double max_score; 
    private Long class_id;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    public Assignment(String title, String description, Date due_date, double max_score) {
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.max_score = max_score;
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }
}

