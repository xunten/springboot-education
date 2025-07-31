package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.UpdateUserDTO;
import com.example.springboot_education.dtos.UserCreateDTO;
import com.example.springboot_education.dtos.UserDTO;
import com.example.springboot_education.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserCreateDTO dto) {
        return userService.createUser(dto);
    }
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody UpdateUserDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
