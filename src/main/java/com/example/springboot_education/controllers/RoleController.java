package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.roleDTOs.CreateRoleRequestDto;
import com.example.springboot_education.dtos.roleDTOs.RoleResponseDto;
import com.example.springboot_education.dtos.roleDTOs.UpdateRoleRequestDto;
import com.example.springboot_education.dtos.usersDTOs.UserIdsRequestDto;
import com.example.springboot_education.dtos.usersDTOs.UserResponseDto;
import com.example.springboot_education.entities.Role;
import com.example.springboot_education.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping()
    public Role create(@RequestBody @Valid CreateRoleRequestDto createRoleRequestDto) {
        return roleService.createRole(createRoleRequestDto);
    }

    @PatchMapping("/{id}")
    public Role update(@PathVariable("id") Long id, @RequestBody @Valid UpdateRoleRequestDto updateRoleRequestDto) {
        return roleService.updateRole(id, updateRoleRequestDto);
    }

    @GetMapping()
    public Iterable<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    // GET ROLE BY ID
    @GetMapping("/{id}")
    public RoleResponseDto getRoleById(@PathVariable("id") Long id) {
        return this.roleService.getRoleById(id);
    }

    // DELETE ROLE BY ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        this.roleService.deleteRole(id);
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
