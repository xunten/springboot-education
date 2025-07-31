package com.example.springboot_education.repositories;

import com.example.springboot_education.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
