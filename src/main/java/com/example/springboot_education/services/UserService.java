package com.example.springboot_education.services;
import com.example.springboot_education.dtos.roleDTOs.RoleResponseDto;
import com.example.springboot_education.entities.Role;
import com.example.springboot_education.entities.UserRole;
import com.example.springboot_education.entities.UserRoleId;
import com.example.springboot_education.entities.Users;
import com.example.springboot_education.exceptions.HttpException;
import com.example.springboot_education.repositories.RoleJpaRepository;
import com.example.springboot_education.repositories.UserRoleJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.springboot_education.dtos.usersDTOs.CreateUserRequestDto;
import com.example.springboot_education.dtos.usersDTOs.UpdateUserRequestDto;
import com.example.springboot_education.dtos.usersDTOs.UserResponseDto;
import com.example.springboot_education.repositories.UsersJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UsersJpaRepository userJpaRepository;

    private final RoleJpaRepository roleJpaRepository;

    private final UserRoleJpaRepository userRoleJpaRepository;

    public UserService(UsersJpaRepository userJpaRepository, RoleJpaRepository roleJpaRepository, UserRoleJpaRepository userRoleJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
        this.userRoleJpaRepository = userRoleJpaRepository;
    }

    private UserResponseDto convertToDto(Users user) {
        List<RoleResponseDto> roles = user.getUserRoles().stream()
                .map(userRole -> new RoleResponseDto(userRole.getRole().getId(), userRole.getRole().getName(),userRole.getRole().getCreatedAt(), userRole.getRole().getUpdatedAt()))
                .collect(Collectors.toList());

        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .imageUrl(user.getImageUrl())
                .email(user.getEmail())
                .roles(roles) // Assuming getRoles() returns a List<Role>
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    // Lấy toàn bộ user
//    public List<UserResponseDto> getAllUsers() {
//        return userJpaRepository.findAll()
//                .stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }

    // @Cacheable(value = "users", key = "'all'")
    public List<UserResponseDto> getUsers() {
        List<Users> users = userJpaRepository.findAllUsersWithRoles();

        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Tạo user mới
    public UserResponseDto createUser(CreateUserRequestDto dto) {
        if (userJpaRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại.");
        }
        // Check if email already exists
        if (this.userJpaRepository.existsByEmail(dto.getEmail())) {
            throw new HttpException("Email already exists: " + dto.getEmail(), HttpStatus.CONFLICT);
        }

        Users user = new Users();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFullName(dto.getFull_name());
        user.setImageUrl(dto.getImage_url());

        // Gán vai trò
        List<UserRole> userRoles = dto.getRole_id().stream().map(roleId -> {
            Role role = roleJpaRepository.findById(roleId)
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy role với id: " + roleId));
            UserRole userRole = new UserRole();
            userRole.setId(new UserRoleId(user.getId(), role.getId())); // Cẩn thận: user.getId() lúc này là null (fix bên dưới)
            userRole.setUser(user);
            userRole.setRole(role);
            return userRole;
        }).collect(Collectors.toList());

        user.setUserRoles(userRoles);

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
    @Transactional
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto dto) {
        Users user = userJpaRepository.findById(id).orElseThrow(() -> new HttpException("User not found with id: " + id, HttpStatus.NOT_FOUND));
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFull_name());
        user.setImageUrl(dto.getImage_url());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(dto.getPassword());
        }

        // ✅ Nếu role_id được gửi lên thì mới cập nhật
        // 🧨 Xử lý update roles (nếu có)
        if (dto.getRole_id() != null) {
            user.getUserRoles().clear(); // <- Quan trọng!

            for (Long roleId : dto.getRole_id()) {
                Role role = roleJpaRepository.findById(roleId)
                        .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy role với id: " + roleId));
                UserRole userRole = new UserRole();
                userRole.setId(new UserRoleId(user.getId(), role.getId()));
                userRole.setUser(user);
                userRole.setRole(role);
                user.getUserRoles().add(userRole);
            }
        }

        Users updatedUser = userJpaRepository.save(user);
        return convertToDto(updatedUser);
    }

    // Xoá
    public void deleteUser(Long id) {
        userJpaRepository.findById(id).orElseThrow();
        userJpaRepository.deleteById(id);
    }

    public boolean isCurrentUser(Long userId, String currentUserEmail) {
        Optional<Users> user = userJpaRepository.findById(userId);
        return user.isPresent() && user.get().getEmail().equals(currentUserEmail);
    }
}
