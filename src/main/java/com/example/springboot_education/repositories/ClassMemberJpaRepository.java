package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.ClassMember;
import com.example.springboot_education.entities.ClassMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassMemberJpaRepository extends JpaRepository<ClassMember, ClassMemberId> {
}
