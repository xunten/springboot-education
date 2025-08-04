package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.AssignmentComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentCommentJpaRepository extends JpaRepository<AssignmentComment, Long> {
    List<AssignmentComment> findByAssignmentId(Long assignmentId);
}
