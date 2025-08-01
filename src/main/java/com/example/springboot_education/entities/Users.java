package com.example.springboot_education.entities;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

public class Users {
    public enum Role {
    ADMIN,
    STUDENT,
    TEACHER
}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    private String email; 
   @Column(name = "full_name")
    private String fullName;

    @Column(name = "image_url")
    private String imageUrl;

     @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "updated_at")
    private Timestamp updated_at;
    
    public Users(String username, String password, String fullName, String email, String imageUrl, Role role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.imageUrl = imageUrl; 
        this.role = role;
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }
@PrePersist
    protected void onCreate() {
        created_at = new Timestamp(System.currentTimeMillis());
        updated_at = new Timestamp(System.currentTimeMillis());
    }
    
    @PreUpdate
    protected void onUpdate() {
        updated_at = new Timestamp(System.currentTimeMillis());
    }
}
    