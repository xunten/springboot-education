package com.example.springboot_education.controllers;

import com.example.springboot_education.dtos.usersDTOs.CreateUserRequestDto;
import com.example.springboot_education.dtos.usersDTOs.UpdateUserRequestDto;
import com.example.springboot_education.dtos.usersDTOs.UserResponseDto;
import com.example.springboot_education.services.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
@GetMapping("")
public List<UserResponseDto> getAllUsers() {
    return userService.getAllUsers();
}
   
    // GET USER BY ID
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }
    

    // CREATE NEW USER
    @PostMapping("")
    public UserResponseDto createUser(@RequestBody @Valid CreateUserRequestDto dto) {
        return this.userService.createUser(dto);
    }

    // UPDATE USER BY ID
    @PatchMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserRequestDto dto) {
        return this.userService.updateUser(id, dto);
    }

    // DELETE USER BY ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
    }
}
