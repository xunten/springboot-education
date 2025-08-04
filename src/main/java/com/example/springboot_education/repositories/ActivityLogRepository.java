package com.example.springboot_education.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_education.entities.ActivityLog;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer> {
    List<ActivityLog> findByUserId(Long userId);
    List<ActivityLog> findByActionType(String actionType);
}
