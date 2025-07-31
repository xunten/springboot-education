package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.UserRole;
import com.example.springboot_education.entities.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findByUserId(Integer userId);
}
