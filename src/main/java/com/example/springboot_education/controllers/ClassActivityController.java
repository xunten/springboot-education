package com.example.springboot_education.controllers;

import com.example.springboot_education.entities.ClassActivity;
import com.example.springboot_education.services.ClassActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class-activities")
public class ClassActivityController {

    @Autowired
    private ClassActivityService classActivityService;

    @PostMapping
    public ResponseEntity<ClassActivity> createActivity(@RequestBody ClassActivity activity) {
        return ResponseEntity.ok(classActivityService.createActivity(activity));
    }

    @GetMapping
    public ResponseEntity<List<ClassActivity>> getAllActivities() {
        return ResponseEntity.ok(classActivityService.getAllActivities());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Integer id) {
        classActivityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}
