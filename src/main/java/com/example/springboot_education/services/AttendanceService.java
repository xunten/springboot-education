package com.example.springboot_education.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.springboot_education.dtos.attendances.AttendanceCreateDTO;
import com.example.springboot_education.dtos.attendances.AttendanceResponseDTO;
import com.example.springboot_education.dtos.attendances.AttendanceUpdateDTO;
import com.example.springboot_education.entities.Attendance;
import com.example.springboot_education.repositories.AttendanceRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository repository;

    public AttendanceService(AttendanceRepository repository) {
        this.repository = repository;
    }

    public AttendanceResponseDTO create(AttendanceCreateDTO dto) {
        Attendance attendance = new Attendance();
        attendance.setStudentId(dto.getStudentId());
        attendance.setScheduleId(dto.getScheduleId());
        attendance.setStatus(dto.getStatus());
        attendance.setMarkedAt(new Timestamp(System.currentTimeMillis()));

        Attendance saved = repository.save(attendance);
        return mapToDTO(saved);
    }

    public List<AttendanceResponseDTO> getByScheduleId(Integer scheduleId) {
        return repository.findByScheduleId(scheduleId)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AttendanceResponseDTO updateStatus(Integer id, AttendanceUpdateDTO dto) {
        Attendance attendance = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id " + id));

        attendance.setStatus(dto.getStatus());
        Attendance updated = repository.save(attendance);
        return mapToDTO(updated);
    }

    private AttendanceResponseDTO mapToDTO(Attendance entity) {
        AttendanceResponseDTO dto = new AttendanceResponseDTO();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudentId());
        dto.setScheduleId(entity.getScheduleId());
        dto.setStatus(entity.getStatus());
        dto.setMarkedAt(entity.getMarkedAt());
        return dto;
    }
}