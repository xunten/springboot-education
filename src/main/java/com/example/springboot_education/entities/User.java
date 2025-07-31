package com.example.springboot_education.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    private List<ClassEntity> teachingClasses;

    @OneToMany(mappedBy = "user")
    private List<ClassActivity> activities;

    @OneToMany(mappedBy = "student")
    private List<ClassMember> classMemberships;
}
