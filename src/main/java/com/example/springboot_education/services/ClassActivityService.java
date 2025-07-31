// ClassActivityService.java
package com.example.springboot_education.services;

import com.example.springboot_education.dtos.ClassActivityDTO;
import com.example.springboot_education.dtos.CreateClassActivityDTO;
import com.example.springboot_education.entities.ClassActivity;
import com.example.springboot_education.entities.ClassEntity;
import com.example.springboot_education.entities.User;
import com.example.springboot_education.repositories.ClassActivityRepository;
import com.example.springboot_education.repositories.ClassRepository;
import com.example.springboot_education.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassActivityService {

    private final ClassActivityRepository activityRepo;
    private final ClassRepository classRepo;
    private final UserRepository userRepo;

    public List<ClassActivityDTO> getActivitiesByClass(Integer classId) {
    return activityRepo.findByClazzId(classId) 
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
}

    public ClassActivityDTO create(CreateClassActivityDTO dto) {
        ClassEntity clazz = classRepo.findById(dto.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ClassActivity activity = new ClassActivity();
        activity.setActivityType(dto.getActivityType());
        activity.setTargetId(dto.getTargetId());
        activity.setDescription(dto.getDescription());
        activity.setClazz(clazz);
        activity.setUser(user);
        activity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return toDTO(activityRepo.save(activity));
    }

    private ClassActivityDTO toDTO(ClassActivity a) {
        ClassActivityDTO dto = new ClassActivityDTO();
        dto.setId(a.getId());
        dto.setActivityType(a.getActivityType());
        dto.setTargetId(a.getTargetId());
        dto.setDescription(a.getDescription());
        dto.setCreatedAt(a.getCreatedAt());
        dto.setClassId(a.getClazz().getId());
        dto.setUserId(a.getUser().getId());
        return dto;
    }
}
