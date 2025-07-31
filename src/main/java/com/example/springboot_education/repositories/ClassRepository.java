package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
}
