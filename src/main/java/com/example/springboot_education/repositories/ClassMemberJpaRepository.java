package com.example.springboot_education.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot_education.entities.ClassMember;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassMemberJpaRepository extends JpaRepository<ClassMember, Long>{

    
} 
