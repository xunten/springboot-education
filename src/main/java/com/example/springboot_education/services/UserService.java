package com.example.springboot_education.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.springboot_education.dtos.roleDTOs.RoleResponseDto;
import com.example.springboot_education.dtos.usersDTOs.CreateUserRequestDto;
import com.example.springboot_education.dtos.usersDTOs.UpdateUserRequestDto;
import com.example.springboot_education.dtos.usersDTOs.UserResponseDto;
import com.example.springboot_education.entities.UserRole;
import com.example.springboot_education.entities.UserRoleId;
import com.example.springboot_education.entities.Users;
import com.example.springboot_education.exceptions.HttpException;
import com.example.springboot_education.repositories.RoleJpaRepository;
import com.example.springboot_education.repositories.UsersJpaRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UsersJpaRepository userJpaRepository;

    private final RoleJpaRepository roleJpaRepository;



    // Convert Users entity to UserResponseDto
    private UserResponseDto convertToDto(Users user) {
        List<RoleResponseDto> roles = user.getUserRoles().stream()
                .map(userRole -> new RoleResponseDto(userRole.getRole().getId(), userRole.getRole().getName()))
                .collect(Collectors.toList());

        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .imageUrl(user.getImageUrl())
                .email(user.getEmail())
                .roles(roles) // Assuming getRoles() returns a List<Role>
                .build();
    }

    // Lấy toàn bộ user
    public List<UserResponseDto> getAllUsers() {
        List<Users> users = userJpaRepository.findAllUsersWithRoles();
        return users
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Tạo user mới
  public UserResponseDto createUser(CreateUserRequestDto dto) {
    if (this.userJpaRepository.existsByEmail(dto.getEmail())) {
        throw new HttpException("Email already exists: " + dto.getEmail(), HttpStatus.CONFLICT);
    }

    Users user = new Users();
    user.setUsername(dto.getUsername());
    user.setEmail(dto.getEmail());
    user.setFullName(dto.getFullName());
    user.setImageUrl(dto.getImageUrl());
    user.setPassword(dto.getPassword());

    if (user.getUserRoles() == null) {
        user.setUserRoles(new java.util.ArrayList<>());
    }

    if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
        for (Long roleId : dto.getRoles()) {
            var role = roleJpaRepository.findById(roleId)
                    .orElseThrow(() -> new HttpException("Role not found with id: " + roleId, HttpStatus.NOT_FOUND));

            var userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);

            var userRoleId = new UserRoleId();
            userRoleId.setUserId(user.getId()); 
            userRoleId.setRoleId(role.getId());
            userRole.setId(userRoleId);

            user.getUserRoles().add(userRole); 
        }
    }

    Users savedUser = userJpaRepository.save(user); 
    return convertToDto(savedUser);
}

    // public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto)
    // {

    // Users user = new Users();
    // user.setUsername(createUserRequestDto.getUsername());
    // user.setFullName(createUserRequestDto.getFullName());
    // user.setEmail(createUserRequestDto.getEmail());
    // user.setPassword(createUserRequestDto.getPassword());
    // user.setRole(createUserRequestDto.getRole());
    // user.setImageUrl(createUserRequestDto.getImageUrl());
    // Users createdUsers= this.userJpaRepository.save(user);
    // return convertToDto(createdUsers);
    // }
    // GET USER BY ID
    public UserResponseDto getUserById(Long id) {
        Users user = this.userJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToDto(user);
    }

    // Cập nhật
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto dto) {
        Users user = userJpaRepository.findById(id)
        .orElseThrow(() -> new HttpException("User not found with id: " + id, HttpStatus.NOT_FOUND));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setImageUrl(dto.getImageUrl());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(dto.getPassword());
        }

        Users updatedUser = userJpaRepository.save(user);
        return convertToDto(updatedUser);

    }

    // Xoá
    public void deleteUser(Long id) {
        if (!userJpaRepository.existsById(id)) {
            throw new HttpException("User not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        
        userJpaRepository.deleteById(id);
    }


}
