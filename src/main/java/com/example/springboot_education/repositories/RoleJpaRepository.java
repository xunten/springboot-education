package com.example.springboot_education.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot_education.entities.Role;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {
    Role save(Role role);

    Role findByName(String name);

    boolean existsByName(String name);

    Optional<Role> findById(Long id);

//    List<Role> findAll();
@Query("SELECT r FROM Role r")
List<Role> findAllRoles(); // Thay thế cho findAll()
}
