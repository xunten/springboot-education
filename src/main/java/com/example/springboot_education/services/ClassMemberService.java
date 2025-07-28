package com.example.springboot_education.services;

import com.example.springboot_education.dtos.classMemberDTOs.ClassMemberResponseDto;
import com.example.springboot_education.dtos.classMemberDTOs.CreateClassMemberRequestDto;
import com.example.springboot_education.dtos.classMemberDTOs.UpdateClassMemberRequestDto;
import com.example.springboot_education.entities.ClassMember;
import com.example.springboot_education.repositories.ClassMemberJpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassMemberService {

    private final ClassMemberJpaRepository classMemberJpaRepository;

    public ClassMemberService(ClassMemberJpaRepository classMemberJpaRepository) {
        this.classMemberJpaRepository = classMemberJpaRepository;
    }

    private ClassMemberResponseDto convertToDto(ClassMember classMember) {
        return new ClassMemberResponseDto(
                classMember.getId(),
                classMember.getStudent_id(),
                classMember.getJoined_at()
        );
    }

    public List<ClassMemberResponseDto> getAllClassMembers() {
        List<ClassMember> members = classMemberJpaRepository.findAll();
        return members.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ClassMemberResponseDto getById(Long id) {
        ClassMember member = classMemberJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClassMember not found"));
        return convertToDto(member);
    }

    public ClassMemberResponseDto create(CreateClassMemberRequestDto request) {
        ClassMember member = new ClassMember();
        member.setStudent_id(request.getStudent_id());
        member.setJoined_at(new Timestamp(System.currentTimeMillis()));

        ClassMember saved = classMemberJpaRepository.save(member);
        return convertToDto(saved);
    }

    public ClassMemberResponseDto update(Long id, UpdateClassMemberRequestDto request) {
        ClassMember member = classMemberJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClassMember not found"));

        member.setStudent_id(request.getStudent_id());
        member.setJoined_at(request.getJoined_at());

        ClassMember updated = classMemberJpaRepository.save(member);
        return convertToDto(updated);
    }

    public void delete(Long id) {
        if (!classMemberJpaRepository.existsById(id)) {
            throw new RuntimeException("ClassMember not found");
        }
        classMemberJpaRepository.deleteById(id);
    }
}
