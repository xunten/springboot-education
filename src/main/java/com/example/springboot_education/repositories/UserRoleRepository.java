package com.example.springboot_education.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_education.entities.UserRole;
import com.example.springboot_education.entities.UserRoleId;

public interface UserRoleRepository  extends JpaRepository<UserRole, UserRoleId> {
      boolean existsById(UserRoleId userRoleId);

    void deleteById(UserRoleId userRoleId);

    // void saveAllUsersRoles(Iterable<UserRole> userRoles);

    // UserRole save(UserRole userRole);
}
