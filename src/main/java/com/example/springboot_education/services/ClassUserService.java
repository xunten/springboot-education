package com.example.springboot_education.services;

import com.example.springboot_education.dtos.classUserDTOs.ClassUserResponseDto;
import com.example.springboot_education.dtos.classUserDTOs.CreateClassUserRequestDto;
import com.example.springboot_education.dtos.classUserDTOs.UpdateClassUserRequestDto;
import com.example.springboot_education.entities.ClassUser;
import com.example.springboot_education.entities.ClassUserId;
import com.example.springboot_education.entities.Users;
import com.example.springboot_education.entities.Class;
import com.example.springboot_education.repositories.UsersJpaRepository;

import lombok.Builder;

import com.example.springboot_education.repositories.ClassJpaRepository;
import com.example.springboot_education.repositories.ClassUserJpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassUserService {

    private final ClassUserJpaRepository classUserJpaRepository;
    private final ClassJpaRepository classJpaRepository;
    private final UsersJpaRepository userJpaRepository;

    public ClassUserService(ClassUserJpaRepository classUserJpaRepository,
                              ClassJpaRepository classJpaRepository,
                              UsersJpaRepository userJpaRepository) {
        this.classUserJpaRepository = classUserJpaRepository;
        this.classJpaRepository = classJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

  private ClassUserResponseDto convertToDto(ClassUser classUser) {
    // Use builder if available, or update to match the actual constructor
    return ClassUserResponseDto.builder()
        .classId(classUser.getId().getClass_id())
        .studentId(classUser.getId().getStudent_id())
        .joinedAt(classUser.getJoinedAt())
        .build();
}

    public List<ClassUserResponseDto> getAllClassUsers() {
        return classUserJpaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ClassUserResponseDto getById(ClassUserId id) {
        ClassUser User = classUserJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClassUser not found"));
        return convertToDto(User);
    }

    public ClassUserResponseDto create(CreateClassUserRequestDto request) {
        Class clazz = classJpaRepository.findById(request.getClass_id())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        Users student = userJpaRepository.findById(request.getStudent_id())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        ClassUserId id = new ClassUserId(request.getClass_id(), request.getStudent_id());

        ClassUser User = ClassUser.builder()
                .id(id)
                .aClass(clazz)
                .student(student)
                .joinedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        ClassUser saved = classUserJpaRepository.save(User);
        return convertToDto(saved);
    }

    public ClassUserResponseDto update(ClassUserId id, UpdateClassUserRequestDto request) {
    ClassUser User = classUserJpaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ClassUser not found"));

    User.setJoinedAt(request.getJoined_at());

    ClassUser updated = classUserJpaRepository.save(User);
    return convertToDto(updated);
}


    public void delete(ClassUserId id) {
        if (!classUserJpaRepository.existsById(id)) {
            throw new RuntimeException("ClassUser not found");
        }
        classUserJpaRepository.deleteById(id);
    }
}
