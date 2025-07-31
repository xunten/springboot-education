package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
