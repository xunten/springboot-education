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

// public UserResponseDto convertToDto(Users user) {
//     return UserResponseDto.builder()
//         .id(user.getId())
//         .username(user.getUsername())
//         .fullName(user.getFullName()) 
//         .imageUrl(user.getImageUrl())
//         .email(user.getEmail())
//         .role(user.getRole())
//         .build();
// }
  public UserResponseDto convertToDto(Users user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setImageUrl(user.getImageUrl());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        return userDto;
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
System.out.println("DTO full_name = " + dto.getFullName());
    Users user = new Users();
    user.setUsername(dto.getUsername());
    user.setEmail(dto.getEmail());
    user.setFullName(dto.getFullName());
    user.setImageUrl(dto.getImageUrl());
    user.setRole(dto.getRole());
    user.setPassword(dto.getPassword());
    Users savedUser = userJpaRepository.save(user);
    return convertToDto(savedUser);
}

    //     public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {

    //    Users user = new Users();
    //     user.setUsername(createUserRequestDto.getUsername());
    //     user.setFullName(createUserRequestDto.getFullName());
    //     user.setEmail(createUserRequestDto.getEmail());
    //     user.setPassword(createUserRequestDto.getPassword());
    //     user.setRole(createUserRequestDto.getRole());
    //     user.setImageUrl(createUserRequestDto.getImageUrl());
    //     Users createdUsers= this.userJpaRepository.save(user);
    //     return convertToDto(createdUsers);
    // }
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
        user.setFullName(dto.getFullName());
        user.setImageUrl(dto.getImageUrl());
        user.setRole(dto.getRole());
// Update timestamp
        user.setUpdated_at(new java.sql.Timestamp(System.currentTimeMillis()));
        
        Users updatedUser = userJpaRepository.save(user);
        return convertToDto(updatedUser);
   
    }

    // Xoá
    public void deleteUser(Long id) {
        userJpaRepository.findById(id).orElseThrow();
        userJpaRepository.deleteById(id);
    }
}
