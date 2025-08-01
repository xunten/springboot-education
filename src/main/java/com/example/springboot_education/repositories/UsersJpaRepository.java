package com.example.springboot_education.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot_education.entities.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersJpaRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByEmailWithRoles(String email);

    Optional<Users> findById(String id);

    boolean existsById(String id);

    Users save(Users user);

    void deleteById(String id);

    List<Users> findAllUsersWithRoles();

    List<Users> findByIdIn(List<String> ids);
}
