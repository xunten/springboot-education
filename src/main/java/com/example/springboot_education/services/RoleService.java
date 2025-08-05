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
import com.example.springboot_education.dtos.usersDTOs.UserSummaryDto;
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

   public static RoleResponseDto toRoleDto(Role role) {
    List<UserSummaryDto> users = role.getUserRoles().stream()
            .map(userRole -> {
                Users user = userRole.getUser();
                return UserSummaryDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .build();
            })
            .collect(Collectors.toList());

    return RoleResponseDto.builder()
            .id(role.getId())
            .name(role.getName())
            .createdAt(role.getCreatedAt())
            .updatedAt(role.getUpdatedAt())
            .users(users)
            .build();
}

    public Role create(CreateRoleRequestDto data) {
        Role role = new Role();
        role.setName(data.getName());
        return this.roleRepository.save(role);
    }

    public Role update(Long id, UpdateRoleRequestDto role) {
        Role existingRole = this.roleRepository.findById(id)
                .orElseThrow(() -> new HttpException("Role not found with id: " + id, HttpStatus.NOT_FOUND));

        if (!role.hasAnyField()) {
            throw new HttpException("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        if (role.getName() != null) {
            existingRole.setName(role.getName());
        }

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
        List<Users> users = userRepository.findByIdIn(userIds);
        Set<Long> foundUserIds = users.stream().map(Users::getId).collect(Collectors.toSet());

        for (Long userId : userIds) {
            if (!foundUserIds.contains(userId)) {
                throw new HttpException("User not found with id: " + userId, HttpStatus.BAD_REQUEST);
            }
        }

        Role role = this.roleRepository.findById(roleId)
                .orElseThrow(() -> new HttpException("Role not found with id: " + roleId, HttpStatus.NOT_FOUND));

        for (Long userId : userIds) {
            UserRoleId userRoleId = new UserRoleId(userId, roleId);
            boolean exists = userRoleRepository.existsById(userRoleId);

            if (exists) continue;

            UserRole userRole = new UserRole();
            userRole.setId(userRoleId);
            Users user = users.stream().filter(u -> u.getId().equals(userId)).findFirst().orElse(null);
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleRepository.save(userRole);

            eventPublisher.publishEvent(new RoleAssignedEvent(user.getId(), roleId));
        }
    }

    public void removeUsersFromRole(Long roleId, List<Long> userIds) {
        Role role = this.roleRepository.findById(roleId).orElse(null);

        if (role == null) {
            throw new HttpException("Role not found with id: " + roleId, HttpStatus.BAD_REQUEST);
        }

        List<Users> users = userRepository.findByIdIn(userIds);
        Set<Long> foundUserIds = users.stream().map(Users::getId).collect(Collectors.toSet());

        for (Long userId : userIds) {
            if (!foundUserIds.contains(userId)) {
                throw new HttpException("User not found with id: " + userId, HttpStatus.BAD_REQUEST);
            }
        }

        for (Long userId : userIds) {
            UserRoleId userRoleId = new UserRoleId(userId, roleId);
            userRoleRepository.deleteById(userRoleId);
            eventPublisher.publishEvent(new RoleUnassignedEvent(userId, roleId));
        }
    }
}
