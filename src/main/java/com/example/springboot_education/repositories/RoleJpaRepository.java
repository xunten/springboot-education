package com.example.springboot_education.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_education.entities.Role;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {
    
}
