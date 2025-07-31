package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.ClassActivity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassActivityRepository extends JpaRepository<ClassActivity, Integer> {
    List<ClassActivity> findByClazz_Id(Integer classId);
    List<ClassActivity> findByUser_Id(Integer userId);
}

