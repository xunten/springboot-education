package com.example.springboot_education.services;

import com.example.springboot_education.dtos.roleDTOs.CreateRoleRequestDto;
import com.example.springboot_education.dtos.roleDTOs.RoleResponseDto;
import com.example.springboot_education.dtos.roleDTOs.UpdateRoleRequestDto;
import com.example.springboot_education.dtos.usersDTOs.UserResponseDto;
import com.example.springboot_education.entities.Role;
import com.example.springboot_education.entities.UserRole;
import com.example.springboot_education.entities.UserRoleId;
import com.example.springboot_education.entities.Users;
import com.example.springboot_education.events.RoleAssignedEvent;
import com.example.springboot_education.events.RoleUnassignedEvent;
import com.example.springboot_education.exceptions.HttpException;
import com.example.springboot_education.repositories.RoleJpaRepository;
import com.example.springboot_education.repositories.UserRoleJpaRepository;
import com.example.springboot_education.repositories.UsersJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleJpaRepository roleJpaRepository;
    private final UsersJpaRepository usersJpaRepository;
    private final UserRoleJpaRepository userRoleJpaRepository;

    private final ApplicationEventPublisher eventPublisher;

    public RoleService(RoleJpaRepository roleJpaRepository,
                       UsersJpaRepository usersRepository,
                       UserRoleJpaRepository userRoleJpaRepository,
                       ApplicationEventPublisher eventPublisher) {
        this.roleJpaRepository = roleJpaRepository;
        this.usersJpaRepository = usersRepository;
        this.userRoleJpaRepository = userRoleJpaRepository;
        this.eventPublisher = eventPublisher;
    }

    public static RoleResponseDto convertToDto(Role role) {
        return RoleResponseDto.builder()
                .id(role.getId())
                .name(role.getName())
                .createdAt(role.getCreatedAt())   // ✅ thêm dòng này
                .updatedAt(role.getUpdatedAt())   // ✅ thêm dòng này
                .build();
    }

    // private RoleResponseDto convertToDto(Role role) {
    //     RoleResponseDto roleResponseDto = new RoleResponseDto();

    //     roleResponseDto.setId(role.getId());
    //     roleResponseDto.setName(role.getName());
    //     roleResponseDto.setCreatedAt(role.getCreatedAt());
    //     roleResponseDto.setUpdatedAt(role.getUpdatedAt());

    //     return roleResponseDto;
    // }

    public Role createRole(CreateRoleRequestDto createRoleRequestDto) {
        Role role = new Role();
        role.setName(createRoleRequestDto.getName());

        return this.roleJpaRepository.save(role);
    }

    public Role updateRole(Long id, UpdateRoleRequestDto role) {
        Role existingRole = this.roleJpaRepository.findById(id)
                .orElseThrow(() -> new HttpException("Role not found with id: " + id, HttpStatus.NOT_FOUND));

        // Validate that at least one field is provided
        if (!role.hasAnyField()) {
            throw new HttpException("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        // Only update fields that are present in the request
        if (role.getName() != null) {
            existingRole.setName(role.getName());
        }

        return this.roleJpaRepository.save(existingRole);
    }

    public RoleResponseDto getRoleById(Long id) {
        Role role = this.roleJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return convertToDto(role);
    }

    // Xoá
    public void deleteRole(Long id) {
        roleJpaRepository.findById(id).orElseThrow();
        roleJpaRepository.deleteById(id);
    }

    public Role findRoleById(Long id) {
        return this.roleJpaRepository.findById(id)
                .orElseThrow(() -> new HttpException("Role not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    public List<Role> getAllRoles() {
        return this.roleJpaRepository.findAllRoles();
    }

    @Transactional
    public void addUsersToRole(Long roleId, List<Long> userIds) {

        List<Users> users = usersJpaRepository.findByIdIn(userIds);
        Set<Long> foundUserIds = users.stream().map(Users::getId).collect(Collectors.toSet());

        for (Long userId : userIds) {
            if (!foundUserIds.contains(userId)) {
                throw new HttpException("User not found with id: " + userId, HttpStatus.BAD_REQUEST);
            }
        }

        // Check the User-Role link exists in users_roles
        Role role = this.roleJpaRepository.findById(roleId)
                .orElseThrow(() -> new HttpException("Role not found with id: " + roleId, HttpStatus.NOT_FOUND));

        for (Long userId : userIds) {
            UserRoleId userRoleId = new UserRoleId(userId, roleId);
            boolean exists = userRoleJpaRepository.existsById(userRoleId);

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
            userRoleJpaRepository.save(userRole);

            // Phát sự kiện RoleAssignedEvent
            eventPublisher.publishEvent(new RoleAssignedEvent(user.getId(), roleId));
        }
    }

    public void removeUsersFromRole(Long roleId, List<Long> userIds) {
        // Check if a role exists
        Role role = this.roleJpaRepository.findById(roleId).orElse(null);

        if (role == null) {
            throw new HttpException("Role not found with id: " + roleId, HttpStatus.BAD_REQUEST);
        }

        // 2. Check if all userIds exist
        List<Users> users = usersJpaRepository.findByIdIn(userIds);
        Set<Long> foundUserIds = users.stream().map(Users::getId).collect(Collectors.toSet());

        for (Long userId : userIds) {
            if (!foundUserIds.contains(userId)) {
                throw new HttpException("User not found with id: " + userId, HttpStatus.BAD_REQUEST);
            }
        }

        // 4. Now Safe: delete the UserRoleRepository links
        for (Long userId : userIds) {
            UserRoleId userRoleId = new UserRoleId(userId, roleId);
            userRoleJpaRepository.deleteById(userRoleId);
            // Phát sự kiện RoleUnassignedEvent
            eventPublisher.publishEvent(new RoleUnassignedEvent(userId, roleId));
        }
    }
}
