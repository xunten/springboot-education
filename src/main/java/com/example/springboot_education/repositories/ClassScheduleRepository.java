package com.example.springboot_education.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_education.entities.ClassSchedule;

public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Integer> {

}
