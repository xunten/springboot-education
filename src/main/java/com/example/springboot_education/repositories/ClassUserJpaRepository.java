package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.ClassUser;
import com.example.springboot_education.entities.ClassUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassUserJpaRepository extends JpaRepository<ClassUser, ClassUserId> {
}
