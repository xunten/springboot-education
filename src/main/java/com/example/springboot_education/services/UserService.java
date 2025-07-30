package com.example.springboot_education.services;
import org.springframework.stereotype.Service;

import com.example.springboot_education.dtos.usersDTOs.CreateUserRequestDto;
import com.example.springboot_education.dtos.usersDTOs.UpdateUserRequestDto;
import com.example.springboot_education.dtos.usersDTOs.UserResponseDto;
import com.example.springboot_education.entities.Users;
import com.example.springboot_education.repositories.UsersJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UsersJpaRepository userJpaRepository;

    public UserService(UsersJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    private UserResponseDto convertToDto(Users user) {
        return new UserResponseDto(
            user.getId(),
            user.getUsername(),
             user.getFull_name(),
            user.getEmail(),
            user.getRole()
        );
    }

    // Lấy toàn bộ user
    public List<UserResponseDto> getAllUsers() {
        return userJpaRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Tạo user mới
    public UserResponseDto createUser(CreateUserRequestDto dto) {
        Users user = new Users();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());

        Users savedUser = userJpaRepository.save(user);
        return convertToDto(savedUser);
    }
    // GET USER BY ID
    public UserResponseDto getUserById(Long id) {
        Users user = this.userJpaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToDto(user);
    }

    // Cập nhật
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto dto) {
        Users user = userJpaRepository.findById(id).orElseThrow();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());

        Users updatedUser = userJpaRepository.save(user);
        return convertToDto(updatedUser);
    }

    // Xoá
    public void deleteUser(Long id) {
        userJpaRepository.findById(id).orElseThrow();
        userJpaRepository.deleteById(id);
    }
}
