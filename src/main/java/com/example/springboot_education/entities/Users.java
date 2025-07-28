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
     @Enumerated(EnumType.STRING)
    private Role role;
    private Timestamp created_at;
    
    public Users(String username, String password, String full_name, String email, Role role) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.email = email;
        this.role = role;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }}