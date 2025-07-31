package com.example.springboot_education.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Table(name = "roles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;
}
