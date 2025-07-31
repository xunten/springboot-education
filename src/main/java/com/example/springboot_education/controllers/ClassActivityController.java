// ClassActivityController.java
package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.ClassActivityDTO;
import com.example.springboot_education.dtos.CreateClassActivityDTO;
import com.example.springboot_education.services.ClassActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class-activities")
@RequiredArgsConstructor
public class ClassActivityController {

    private final ClassActivityService activityService;

    @GetMapping("/class/{classId}")
    public List<ClassActivityDTO> getByClass(@PathVariable Integer classId) {
        return activityService.getActivitiesByClass(classId);
    }

    @PostMapping
    public ClassActivityDTO create(@RequestBody CreateClassActivityDTO dto) {
        return activityService.create(dto);
    }
}
