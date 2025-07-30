package com.example.springboot_education.entities;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.*;

@Data
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
    private Timestamp created_at;
    private Timestamp updated_at;
    
    public Users(String username, String password, String fullName, String email, String image_url, Role role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.imageUrl = image_url; 
        this.role = role;
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }}
    