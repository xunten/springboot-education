package com.example.springboot_education.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentJpaRepository extends JpaRepository<Assignment, Long> {
}
