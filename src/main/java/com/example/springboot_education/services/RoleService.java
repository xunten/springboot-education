package com.example.springboot_education.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot_education.dtos.roleDTOs.UpdateRoleRequestDto;
import com.example.springboot_education.dtos.roleDTOs.CreateRoleRequestDto;
import com.example.springboot_education.dtos.roleDTOs.RoleResponseDto;
import com.example.springboot_education.entities.Role;
import com.example.springboot_education.repositories.RoleJpaRepository;
import com.example.springboot_education.repositories.UserRoleRepository;
import com.example.springboot_education.repositories.UsersJpaRepository;
import com.example.springboot_education.entities.Users;
import com.example.springboot_education.entities.UserRole;
import com.example.springboot_education.entities.UserRoleId;
import com.example.springboot_education.exceptions.HttpException;
import com.example.springboot_education.events.RoleAssignedEvent;
import com.example.springboot_education.events.RoleUnassignedEvent;



@Service
public class RoleService {
    private final RoleJpaRepository roleRepository;
    private final UsersJpaRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private final ApplicationEventPublisher eventPublisher;

    public RoleService(RoleJpaRepository roleRepository,
            UsersJpaRepository userRepository,
            UserRoleRepository userRoleRepository,
            ApplicationEventPublisher eventPublisher) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.eventPublisher = eventPublisher;
    }

       public static RoleResponseDto convertToDto(Role role) {
        return RoleResponseDto.builder()
                .id(role.getId())
                .name(role.getName())
                .createdAt(role.getCreatedAt())   
                .updatedAt(role.getUpdatedAt())   
                .build();
    }

    public Role create(CreateRoleRequestDto data) {
        Role role = new Role();
        // role.setCode(data.getCode());
        role.setName(data.getName());
        // role.setDescription(data.getDescription());

        return this.roleRepository.save(role);
    }

    public Role update(Long id, UpdateRoleRequestDto role) {
        Role existingRole = this.roleRepository.findById(id)
                .orElseThrow(() -> new HttpException("Role not found with id: " + id, HttpStatus.NOT_FOUND));

        // Validate that at least one field is provided
        if (!role.hasAnyField()) {
            throw new HttpException("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        // Only update fields that are present in the request
        // if (role.getCode() != null) {
        //     existingRole.setCode(role.getCode());
        // }
        if (role.getName() != null) {
            existingRole.setName(role.getName());
        }
        // if (role.getDescription() != null) {
        //     existingRole.setDescription(role.getDescription());
        // }

        return this.roleRepository.save(existingRole);
    }

    public Role findById(Long id) {
        return this.roleRepository.findById(id)
                .orElseThrow(() -> new HttpException("Role not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }

    @Transactional
    public void addUsersToRole(Long roleId, List<Long> userIds) {

        // Check if all userIds exist
        List<Users> users = userRepository.findByIdIn(userIds);
        Set<Long> foundUserIds = users.stream().map(Users::getId).collect(Collectors.toSet());

        for (Long userId : userIds) {
            if (!foundUserIds.contains(userId)) {
                throw new HttpException("User not found with id: " + userId, HttpStatus.BAD_REQUEST);
            }
        }

        // Check the User-Role link exists in users_roles
        Role role = this.roleRepository.findById(roleId)
                .orElseThrow(() -> new HttpException("Role not found with id: " + roleId, HttpStatus.NOT_FOUND));
        for (Long userId : userIds) {
            UserRoleId userRoleId = new UserRoleId();
            userRoleId.setUserId(userId);
            userRoleId.setRoleId(roleId);
            boolean exists = userRoleRepository.existsById(userRoleId);

            if (exists) {
                // User is already assigned to the role
                continue;
            }

            // If we reach here, the user is not assigned to the role
            UserRole userRole = new UserRole();
            userRole.setId(userRoleId);
            // Set User and Role objects to avoid null one-to-one property error
            Users user = users.stream().filter(u -> u.getId().equals(userId)).findFirst().orElse(null);
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleRepository.save(userRole);

            // Phát sự kiện RoleAssignedEvent
            eventPublisher.publishEvent(new RoleAssignedEvent(user.getId(), roleId));
        }
    }

    public void removeUsersFromRole(Long roleId, List<Long> userIds) {
        // Check if a role exists
        Role role = this.roleRepository.findById(roleId).orElse(null);

        if (role == null) {
            throw new HttpException("Role not found with id: " + roleId, HttpStatus.BAD_REQUEST);
        }

        // 2. Check if all userIds exist
        List<Users> users = userRepository.findByIdIn(userIds);
        Set<Long> foundUserIds = users.stream().map(Users::getId).collect(Collectors.toSet());

        for (Long userId : userIds) {
            if (!foundUserIds.contains(userId)) {
                throw new HttpException("User not found with id: " + userId, HttpStatus.BAD_REQUEST);
            }
        }

        // 4. Now Safe: delete the UserRoleRepository links
        for (Long userId : userIds) {
            UserRoleId userRoleId = new UserRoleId(userId, roleId);
            userRoleRepository.deleteById(userRoleId);
            // Phát sự kiện RoleUnassignedEvent
            eventPublisher.publishEvent(new RoleUnassignedEvent(userId, roleId));
        }
    }
}