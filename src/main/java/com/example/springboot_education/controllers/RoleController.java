package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.roleDTOs.CreateRoleRequestDto;
import com.example.springboot_education.dtos.roleDTOs.UpdateRoleRequestDto;
import com.example.springboot_education.dtos.roleDTOs.RoleResponseDto;
import com.example.springboot_education.dtos.usersDTOs.UserIdsRequestDto;
import com.example.springboot_education.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/security/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping()
    public ResponseEntity<RoleResponseDto> create(@RequestBody @Valid CreateRoleRequestDto data) {
        return ResponseEntity.ok(roleService.toRoleDto(roleService.create(data)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RoleResponseDto> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateRoleRequestDto data) {
        return ResponseEntity.ok(roleService.toRoleDto(roleService.update(id, data)));
    }

   @GetMapping()
public List<RoleResponseDto> getRoles() {
    return roleService.getRoles().stream()
            .map(RoleService::toRoleDto)
            .collect(Collectors.toList());
}


    @PatchMapping("/{id}/add-users-to-role")
    public ResponseEntity<String> addUsersToRole(@PathVariable("id") Long id,
                                                 @RequestBody UserIdsRequestDto request) {
        roleService.addUsersToRole(id, request.getUserIds());
        return ResponseEntity.ok("Users added to role successfully!");
    }

    @PatchMapping("/{id}/remove-users-from-role")
    public ResponseEntity<String> removeUsersFromRole(@PathVariable("id") Long id,
                                                      @RequestBody UserIdsRequestDto request) {
        roleService.removeUsersFromRole(id, request.getUserIds());
        return ResponseEntity.ok("Users removed from role successfully!");
    }
}
