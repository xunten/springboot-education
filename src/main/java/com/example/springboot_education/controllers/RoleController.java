package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.CreateRoleDTO;
import com.example.springboot_education.dtos.RoleDTO;
import com.example.springboot_education.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public RoleDTO createRole(@RequestBody CreateRoleDTO dto) {
        return roleService.createRole(dto);
    }

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
    }
}
