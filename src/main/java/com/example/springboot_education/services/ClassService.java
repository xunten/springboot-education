// ClassService.java
package com.example.springboot_education.services;

import com.example.springboot_education.dtos.AddStudentToClassDTO;
import com.example.springboot_education.dtos.ClassInfoDTO;
import com.example.springboot_education.dtos.ClassMemberDTO;
import com.example.springboot_education.dtos.ClassResponseDTO;
import com.example.springboot_education.dtos.CreateClassDTO;
import com.example.springboot_education.entities.ClassEntity;
import com.example.springboot_education.entities.ClassMember;
import com.example.springboot_education.entities.User;
import com.example.springboot_education.repositories.ClassMemberRepository;
import com.example.springboot_education.repositories.ClassRepository;
import com.example.springboot_education.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final ClassMemberRepository classMemberRepository;

    public List<ClassResponseDTO> getAllClasses() {
        return classRepository.findAll()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ClassResponseDTO getClassById(Integer id) {
        ClassEntity clazz = classRepository.findById(id).orElseThrow();
        return toDTO(clazz);
    }

    public ClassResponseDTO createClass(CreateClassDTO dto) {
        User teacher = userRepository.findById(dto.getTeacherId()).orElseThrow();

        ClassEntity clazz = new ClassEntity();
        clazz.setClassName(dto.getClassName());
        clazz.setSubject(dto.getSubject());
        clazz.setSchoolYear(dto.getSchoolYear());
        clazz.setSemester(dto.getSemester());
        clazz.setDescription(dto.getDescription());
        clazz.setTeacher(teacher);
        clazz.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return toDTO(classRepository.save(clazz));
    }

    public ClassResponseDTO updateClass(Integer id, CreateClassDTO dto) {
        ClassEntity clazz = classRepository.findById(id).orElseThrow();
        User teacher = userRepository.findById(dto.getTeacherId()).orElseThrow();

        clazz.setClassName(dto.getClassName());
        clazz.setSubject(dto.getSubject());
        clazz.setSchoolYear(dto.getSchoolYear());
        clazz.setSemester(dto.getSemester());
        clazz.setDescription(dto.getDescription());
        clazz.setTeacher(teacher);

        return toDTO(classRepository.save(clazz));
    }

    public void deleteClass(Integer id) {
        classRepository.deleteById(id);
    }

    private ClassResponseDTO toDTO(ClassEntity clazz) {
        ClassResponseDTO dto = new ClassResponseDTO();
        dto.setId(clazz.getId());
        dto.setClassName(clazz.getClassName());
        dto.setSubject(clazz.getSubject());
        dto.setSchoolYear(clazz.getSchoolYear());
        dto.setSemester(clazz.getSemester());
        dto.setDescription(clazz.getDescription());
        dto.setCreatedAt(clazz.getCreatedAt());
        dto.setTeacherId(clazz.getTeacher() != null ? clazz.getTeacher().getId() : null);
        return dto;
}

public List<ClassMemberDTO> getStudentsInClass(Integer classId) {
    List<ClassMember> members = classMemberRepository.findByClassId(classId);

    return members.stream()
            .map(member -> {
                User student = member.getStudent();
                ClassMemberDTO dto = new ClassMemberDTO();
                dto.setId(student.getId());
                dto.setFullName(student.getFullName());
                dto.setEmail(student.getEmail());
                return dto;
            })
            .collect(Collectors.toList());
}
public List<ClassInfoDTO> getClassesOfStudent(Integer studentId) {
    List<ClassMember> members = classMemberRepository.findByStudentId(studentId);

    return members.stream()
            .map(member -> {
                ClassEntity clazz = member.getClazz();
                return new ClassInfoDTO(
                        clazz.getId(),
                        clazz.getClassName(),
                        clazz.getSubject(),
                        clazz.getSchoolYear(),
                        clazz.getSemester()
                );
            })
            .collect(Collectors.toList());
}
    public void addStudentToClass(AddStudentToClassDTO dto) {
        // Kiểm tra xem đã tồn tại chưa
        if (classMemberRepository.existsByClassIdAndStudentId(dto.getClassId(), dto.getStudentId())) {
            throw new RuntimeException("Học sinh đã có trong lớp này!");
        }

        ClassEntity clazz = classRepository.findById(dto.getClassId())
                .orElseThrow(() -> new RuntimeException("Lớp học không tồn tại"));

        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Học sinh không tồn tại"));

        ClassMember member = new ClassMember();
        member.setClassId(dto.getClassId());
        member.setStudentId(dto.getStudentId());
        member.setClazz(clazz);
        member.setStudent(student);
        member.setJoinedAt(Timestamp.from(Instant.now()));

        classMemberRepository.save(member);
    }
}
