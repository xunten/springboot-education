package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.UserRole;
import com.example.springboot_education.entities.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleJpaRepository extends JpaRepository<UserRole,Long> {
    boolean existsById(UserRoleId userRoleId);

    void deleteById(UserRoleId userRoleId);

//    void saveAllUsersRoles(Iterable<UserRole> userRoles);
//
//    UserRole save(UserRole userRole);
}
