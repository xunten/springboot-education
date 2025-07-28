package com.example.springboot_education.entities;

import java.security.Timestamp;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    public Assignment(String title, String description, Date due_date, double max_score) {
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.max_score = max_score;
    }
}
