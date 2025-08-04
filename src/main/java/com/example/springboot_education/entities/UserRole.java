package com.example.springboot_education.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    @EmbeddedId
    private UserRoleId id = new UserRoleId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    @Column(nullable = false)
    private boolean enabled = true;
}
