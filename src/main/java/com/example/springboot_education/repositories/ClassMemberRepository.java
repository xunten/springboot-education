
package com.example.springboot_education.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot_education.entities.ClassMember;
import com.example.springboot_education.entities.ClassMemberId;



@Repository
public interface ClassMemberRepository extends JpaRepository<ClassMember, ClassMemberId> {
    List<ClassMember> findByClassId(Integer classId);
    List<ClassMember> findByStudentId(Integer studentId);
    boolean existsByClassIdAndStudentId(Integer classId, Integer studentId);
}

