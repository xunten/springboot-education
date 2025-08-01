package com.example.springboot_education.services;

import com.example.springboot_education.dtos.activitylogs.ActivityLogResponseDTO;
import com.example.springboot_education.repositories.ActivityLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityLogService {
    private final ActivityLogRepository repository;

    public ActivityLogService(ActivityLogRepository repository) {
        this.repository = repository;
    }

    public List<ActivityLogResponseDTO> getAllLogs() {
        return repository.findAll().stream()
                .map(log -> {
                    ActivityLogResponseDTO dto = new ActivityLogResponseDTO();
                    dto.setId(log.getId());
                    dto.setUserId(log.getUserId());
                    dto.setActionType(log.getActionType());
                    dto.setTargetId(log.getTargetId());
                    dto.setCreatedAt(log.getCreatedAt());
                    return dto;
                }).collect(Collectors.toList());
    }
}
