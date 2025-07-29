package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.classMemberDTOs.ClassMemberResponseDto;
import com.example.springboot_education.dtos.classMemberDTOs.CreateClassMemberRequestDto;
import com.example.springboot_education.dtos.classMemberDTOs.UpdateClassMemberRequestDto;
import com.example.springboot_education.entities.ClassMemberId;
import com.example.springboot_education.services.ClassMemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-members")
public class ClassMemberController {

    private final ClassMemberService classMemberService;

    public ClassMemberController(ClassMemberService classMemberService) {
        this.classMemberService = classMemberService;
    }

    @GetMapping("")
    public List<ClassMemberResponseDto> getAllClassMembers() {
        return classMemberService.getAllClassMembers();
    }
@PostMapping("")
    public ClassMemberResponseDto createClassMember(@RequestBody @Valid CreateClassMemberRequestDto requestDto) {
        return classMemberService.create(requestDto);
    }
   @GetMapping("/{classId}/{studentId}")
public ClassMemberResponseDto getClassMemberById(
        @PathVariable("classId") Long classId,
        @PathVariable("studentId") Long studentId) {
    return classMemberService.getById(new ClassMemberId(classId, studentId));
}

@PatchMapping("/{classId}/{studentId}")
public ClassMemberResponseDto updateClassMember(
        @PathVariable("classId") Long classId,
        @PathVariable("studentId") Long studentId,
        @RequestBody @Valid UpdateClassMemberRequestDto requestDto) {
    return classMemberService.update(new ClassMemberId(classId, studentId), requestDto);
}

@DeleteMapping("/{classId}/{studentId}")
public void deleteClassMember(
        @PathVariable("classId") Long classId,
        @PathVariable("studentId") Long studentId) {
    classMemberService.delete(new ClassMemberId(classId, studentId));
}

}
