package com.example.springboot_education.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(length = 50)
    private String username;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String password;

    @Size(max = 100)
    private String fullName;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false)
    private String email;

    @Size(max = 255)
    private String imageUrl;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    // ✅ Method để lấy Roles từ bảng trung gian userRoles
    public Set<Role> getRoles() {
        Set<Role> roles = new HashSet<>();
        for (UserRole ur : this.userRoles) {
            roles.add(ur.getRole());
        }
        return roles;
    } 
}
