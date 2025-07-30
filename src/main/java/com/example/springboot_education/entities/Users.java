package com.example.springboot_education.entities;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
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
    private String full_name;
    private String email; 
    private String image_url;
     @Enumerated(EnumType.STRING)
    private Role role;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    public Users(String username, String password, String full_name, String email, String image_url, Role role) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.email = email;
        this.image_url = image_url; 
        this.role = role;
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }}
    