package com.example.springboot_education.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot_education.entities.Assignment;

@Repository
public interface AssignmentJpaRepository extends JpaRepository<Assignment, Long> {
}
