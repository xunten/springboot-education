package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.ClassActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClassActivityRepository extends JpaRepository<ClassActivity, Integer> {
    List<ClassActivity> findByClazzId(Integer classId);
    List<ClassActivity> findByUserId(Integer userId);
}