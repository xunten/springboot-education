package com.example.springboot_education.services;

import com.example.springboot_education.entities.ClassActivity;
import com.example.springboot_education.repositories.ClassActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassActivityService {

    @Autowired
    private ClassActivityRepository classActivityRepository;

    public ClassActivity createActivity(ClassActivity activity) {
        return classActivityRepository.save(activity);
    }

    public List<ClassActivity> getAllActivities() {
        return classActivityRepository.findAll();
    }

    public void deleteActivity(Integer id) {
        classActivityRepository.deleteById(id);
    }
}
