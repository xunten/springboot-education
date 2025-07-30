package com.example.springboot_education.services;

import com.example.springboot_education.dtos.classMemberDTOs.ClassMemberResponseDto;
import com.example.springboot_education.dtos.classMemberDTOs.CreateClassMemberRequestDto;
import com.example.springboot_education.dtos.classMemberDTOs.UpdateClassMemberRequestDto;
import com.example.springboot_education.entities.ClassMember;
import com.example.springboot_education.entities.ClassMemberId;
import com.example.springboot_education.entities.Users;
import com.example.springboot_education.entities.Class;
import com.example.springboot_education.repositories.UsersJpaRepository;
import com.example.springboot_education.repositories.ClassJpaRepository;
import com.example.springboot_education.repositories.ClassMemberJpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassMemberService {

    private final ClassMemberJpaRepository classMemberJpaRepository;
    private final ClassJpaRepository classJpaRepository;
    private final UsersJpaRepository userJpaRepository;

    public ClassMemberService(ClassMemberJpaRepository classMemberJpaRepository,
                              ClassJpaRepository classJpaRepository,
                              UsersJpaRepository userJpaRepository) {
        this.classMemberJpaRepository = classMemberJpaRepository;
        this.classJpaRepository = classJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

  private ClassMemberResponseDto convertToDto(ClassMember classMember) {
    return new ClassMemberResponseDto(
        classMember.getId().getClass_id(),         
        classMember.getId().getStudent_id(),       
        classMember.getJoinedAt()                
    );
}

    public List<ClassMemberResponseDto> getAllClassMembers() {
        return classMemberJpaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ClassMemberResponseDto getById(ClassMemberId id) {
        ClassMember member = classMemberJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClassMember not found"));
        return convertToDto(member);
    }

    public ClassMemberResponseDto create(CreateClassMemberRequestDto request) {
        Class clazz = classJpaRepository.findById(request.getClass_id())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        Users student = userJpaRepository.findById(request.getStudent_id())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        ClassMemberId id = new ClassMemberId(request.getClass_id(), request.getStudent_id());

        ClassMember member = ClassMember.builder()
                .id(id)
                .aClass(clazz)
                .student(student)
                .joinedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        ClassMember saved = classMemberJpaRepository.save(member);
        return convertToDto(saved);
    }

    public ClassMemberResponseDto update(ClassMemberId id, UpdateClassMemberRequestDto request) {
    ClassMember member = classMemberJpaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ClassMember not found"));

    member.setJoinedAt(request.getJoined_at());

    ClassMember updated = classMemberJpaRepository.save(member);
    return convertToDto(updated);
}


    public void delete(ClassMemberId id) {
        if (!classMemberJpaRepository.existsById(id)) {
            throw new RuntimeException("ClassMember not found");
        }
        classMemberJpaRepository.deleteById(id);
    }
}
