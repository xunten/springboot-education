package com.example.springboot_education.services;

import com.example.springboot_education.dtos.UpdateUserDTO;
import com.example.springboot_education.dtos.UserCreateDTO;
import com.example.springboot_education.dtos.UserDTO;
import com.example.springboot_education.entities.User;
import com.example.springboot_education.entities.UserRole;
import com.example.springboot_education.entities.UserRoleId;
import com.example.springboot_education.repositories.RoleRepository;
import com.example.springboot_education.repositories.UserRepository;
import com.example.springboot_education.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    public UserDTO createUser(UserCreateDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // chưa mã hóa, nên thêm mã hóa sau
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        user = userRepository.save(user);

        for (Integer roleId : dto.getRoleIds()) {
            UserRole ur = new UserRole();
            ur.setUserId(user.getId());
            ur.setRoleId(roleId);
            userRoleRepository.save(ur);
        }

        return getUserById(user.getId());
    }
    public UserDTO updateUser(Integer id, UpdateUserDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        return convertToDTO(userRepository.save(user));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
    UserDTO dto = new UserDTO();
    dto.setId(user.getId());
    dto.setUsername(user.getUsername());
    dto.setFullName(user.getFullName());
    dto.setEmail(user.getEmail());
    dto.setCreatedAt(user.getCreatedAt());
    
    // FIX: Kiểm tra null trước khi gọi stream()
    // List<Integer> roleIds = new ArrayList<>();
    // if (user.getRoles() != null) {
    //     roleIds = user.getRoles().stream()
    //                 .map(r -> r.getRole().getId())
    //                 .collect(Collectors.toList());
    // }
    // dto.setRoleIds(roleIds);

    List<String> roleNames = user.getRoles().stream()
        .map(r -> r.getRole().getName())
        .collect(Collectors.toList());
    dto.setRoleNames(roleNames);
    // List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
    // List<Integer> roleIds = userRoles.stream()
    //     .map(ur -> ur.getRole().getId())  // hoặc ur.getRoleId() nếu không cần join
    //     .collect(Collectors.toList());

    // dto.setRoleIds(roleIds);
    
    return dto;
}
}
