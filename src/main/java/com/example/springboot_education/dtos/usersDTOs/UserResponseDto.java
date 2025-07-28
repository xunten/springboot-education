package com.example.springboot_education.dtos.usersDTOs;

import com.example.springboot_education.entities.Users;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
     private Long id;
    private String username;
    private String full_name;
    private String email;
     private Users.Role role;
}
