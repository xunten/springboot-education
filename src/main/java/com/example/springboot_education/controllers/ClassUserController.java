package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.classUserDTOs.ClassUserResponseDto;
import com.example.springboot_education.dtos.classUserDTOs.CreateClassUserRequestDto;
import com.example.springboot_education.dtos.classUserDTOs.UpdateClassUserRequestDto;
import com.example.springboot_education.entities.ClassUserId;
import com.example.springboot_education.services.ClassUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-user")
public class ClassUserController {

    private final ClassUserService classUserService;

    public ClassUserController(ClassUserService classUserService) {
        this.classUserService = classUserService;
    }

    @GetMapping("")
    public List<ClassUserResponseDto> getAllClassUsers() {
        return classUserService.getAllClassUsers();
    }
@PostMapping("")
    public ClassUserResponseDto createClassUser(@RequestBody @Valid CreateClassUserRequestDto requestDto) {
        return classUserService.create(requestDto);
    }
   @GetMapping("/{classId}/{studentId}")
public ClassUserResponseDto getClassUserById(
        @PathVariable("classId") Long classId,
        @PathVariable("studentId") Long studentId) {
    return classUserService.getById(new ClassUserId(classId, studentId));
}

@PatchMapping("/{classId}/{studentId}")
public ClassUserResponseDto updateClassUser(
        @PathVariable("classId") Long classId,
        @PathVariable("studentId") Long studentId,
        @RequestBody @Valid UpdateClassUserRequestDto requestDto) {
    return classUserService.update(new ClassUserId(classId, studentId), requestDto);
}

@DeleteMapping("/{classId}/{studentId}")
public void deleteClassUser(
        @PathVariable("classId") Long classId,
        @PathVariable("studentId") Long studentId) {
    classUserService.delete(new ClassUserId(classId, studentId));
}

}
