package com.example.springboot_education.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot_education.entities.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersJpaRepository extends JpaRepository<Users, Long> {
    
}
