package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.AddStudentToClassDTO;
import com.example.springboot_education.dtos.ClassInfoDTO;
import com.example.springboot_education.dtos.ClassMemberDTO;
import com.example.springboot_education.dtos.ClassResponseDTO;
import com.example.springboot_education.dtos.CreateClassDTO;
import com.example.springboot_education.services.ClassService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @GetMapping
    public List<ClassResponseDTO> getAllClasses() {
        return classService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ClassResponseDTO getClassById(@PathVariable Integer id) {
        return classService.getClassById(id);
    }

    @PostMapping
    public ClassResponseDTO createClass(@RequestBody CreateClassDTO dto) {
        return classService.createClass(dto);
    }

    @PutMapping("/{id}")
    public ClassResponseDTO updateClass(@PathVariable Integer id, @RequestBody CreateClassDTO dto) {
        return classService.updateClass(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable Integer id) {
        classService.deleteClass(id);
    }
    @GetMapping("/{classId}/students")
    public ResponseEntity<List<ClassMemberDTO>> getStudentsInClass(@PathVariable Integer classId) {
        List<ClassMemberDTO> students = classService.getStudentsInClass(classId);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/students/{studentId}/classes")
    public ResponseEntity<List<ClassInfoDTO>> getClassesOfStudent(@PathVariable Integer studentId) {
        List<ClassInfoDTO> classes = classService.getClassesOfStudent(studentId);
        return ResponseEntity.ok(classes);
    }
    @PostMapping("/add-student")
    public ResponseEntity<?> addStudentToClass(@RequestBody AddStudentToClassDTO dto) {
        classService.addStudentToClass(dto);
        return ResponseEntity.ok("Thêm học sinh vào lớp thành công");
    }

}
