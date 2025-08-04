package com.example.springboot_education.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.springboot_education.dtos.classschedules.ClassScheduleCreateDTO;
import com.example.springboot_education.dtos.classschedules.ClassScheduleResponseDTO;
import com.example.springboot_education.dtos.classschedules.ClassScheduleUpdateDTO;
import com.example.springboot_education.entities.ClassSchedule;
import com.example.springboot_education.repositories.ClassScheduleRepository;

@Service
public class ClassScheduleService {
    private final ClassScheduleRepository repository;

    public ClassScheduleService(ClassScheduleRepository repository) {
        this.repository = repository;
    }

    public ClassScheduleResponseDTO create(ClassScheduleCreateDTO dto) {
    ClassSchedule schedule = new ClassSchedule();
    schedule.setClassId(dto.getClassId());
    schedule.setDayOfWeek(dto.getDayOfWeek());
    schedule.setStartTime(dto.getStartTime());
    schedule.setEndTime(dto.getEndTime());
    schedule.setLocation(dto.getLocation());
    schedule.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    schedule.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    ClassSchedule saved = repository.save(schedule);
    return mapToDTO(saved);
}

    public List<ClassScheduleResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ClassScheduleResponseDTO update(Integer id, ClassScheduleUpdateDTO dto) {
    ClassSchedule schedule = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Class Schedule not found"));

    schedule.setDayOfWeek(dto.getDayOfWeek());
    schedule.setStartTime(dto.getStartTime());
    schedule.setEndTime(dto.getEndTime());
    schedule.setLocation(dto.getLocation());
    schedule.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

    ClassSchedule updated = repository.save(schedule);
    return mapToDTO(updated);
}

    private ClassScheduleResponseDTO mapToDTO(ClassSchedule schedule) {
    ClassScheduleResponseDTO dto = new ClassScheduleResponseDTO();
    dto.setId(schedule.getId());
    dto.setClassId(schedule.getClassId());
    dto.setDayOfWeek(schedule.getDayOfWeek());
    dto.setStartTime(schedule.getStartTime());
    dto.setEndTime(schedule.getEndTime());
    dto.setLocation(schedule.getLocation());
    dto.setCreatedAt(schedule.getCreatedAt());
    dto.setUpdatedAt(schedule.getUpdatedAt());
    return dto;
}

}
