package com.example.springboot_education.repositories;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springboot_education.entities.Class;
@Repository
public interface ClassJpaRepository extends JpaRepository<Class, Long> {
    // List<Class> findByTeacher_Id(Integer teacherId);
    
}
