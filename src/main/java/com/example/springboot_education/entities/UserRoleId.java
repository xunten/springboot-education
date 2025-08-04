package com.example.springboot_education.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// public class UserRoleId implements Serializable {
//     private static final long serialVersionUID = 552538177286132061L;
//     @NotNull
//     @Column(name = "user_id", nullable = false)
//     private Long userId;

//     @NotNull
//     @Column(name = "role_id", nullable = false)
//     private Long roleId;

//     @Override
//     public boolean equals(Object o) {
//         if (this == o) return true;
//         if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//         UserRoleId entity = (UserRoleId) o;
//         return Objects.equals(this.roleId, entity.roleId) &&
//                 Objects.equals(this.userId, entity.userId);
//     }

//     @Override
//     public int hashCode() {
//         return Objects.hash(roleId, userId);
//     }

// }

public class UserRoleId implements Serializable {
    private Long userId;
    private Long roleId;
}