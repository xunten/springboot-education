package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.classMemberDTOs.ClassMemberResponseDto;
import com.example.springboot_education.dtos.classMemberDTOs.CreateClassMemberRequestDto;
import com.example.springboot_education.dtos.classMemberDTOs.UpdateClassMemberRequestDto;
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

    @GetMapping("/{id}")
    public ClassMemberResponseDto getClassMemberById(@PathVariable("id") Long id) {
        return classMemberService.getById(id);
    }

    @PostMapping("")
    public ClassMemberResponseDto createClassMember(@RequestBody @Valid CreateClassMemberRequestDto requestDto) {
        return classMemberService.create(requestDto);
    }

    @PatchMapping("/{id}")
    public ClassMemberResponseDto updateClassMember(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateClassMemberRequestDto requestDto) {
        return classMemberService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteClassMember(@PathVariable("id") Long id) {
        classMemberService.delete(id);
    }
}
