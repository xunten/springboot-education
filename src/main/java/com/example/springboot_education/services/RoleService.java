package com.example.springboot_education.services;

import com.example.springboot_education.dtos.CreateRoleDTO;
import com.example.springboot_education.dtos.RoleDTO;
import com.example.springboot_education.entities.Role;
import com.example.springboot_education.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleDTO createRole(CreateRoleDTO dto) {
        Role role = new Role();
        role.setName(dto.getName());
        role = roleRepository.save(role);

        RoleDTO result = new RoleDTO();
        result.setId(role.getId());
        result.setName(role.getName());
        return result;
    }

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(role -> {
                    RoleDTO dto = new RoleDTO();
                    dto.setId(role.getId());
                    dto.setName(role.getName());
                    return dto;
                }).collect(Collectors.toList());
    }

    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }
}
