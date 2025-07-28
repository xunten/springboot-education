package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.FinalGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FinalGradeRepository extends JpaRepository<FinalGrade, Long> {
    List<FinalGrade> findByClassId(Long classId);
    List<FinalGrade> findByStudentId(Long studentId);
    List<FinalGrade> findByClassIdAndSemester(Long classId, String semester);
}
