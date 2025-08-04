package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<Role,Long> {
    Role save(Role role);

    Role findByName(String name);

    boolean existsByName(String name);

    Optional<Role> findById(Long id);

//    List<Role> findAll();
@Query("SELECT r FROM Role r")
List<Role> findAllRoles(); // Thay thế cho findAll()
}
