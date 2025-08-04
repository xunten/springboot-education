package com.example.springboot_education.repositories;
import com.example.springboot_education.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersJpaRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<Users> findByEmail(String email);

    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.userRoles WHERE u.email = :email")
    Optional<Users> findByEmailWithRoles(String email);

    Optional<Users> findById(Long id);

    boolean existsById(Long id);

    Users save(Users users);

    void deleteById(Long id);

    @Query("SELECT DISTINCT u FROM Users u LEFT JOIN FETCH u.userRoles")
    List<Users> findAllUsersWithRoles();

    List<Users> findByIdIn(List<Long> ids);
}
