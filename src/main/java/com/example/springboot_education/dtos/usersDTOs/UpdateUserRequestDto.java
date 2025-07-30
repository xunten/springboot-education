package com.example.springboot_education.dtos.usersDTOs;

import com.example.springboot_education.entities.Users.Role;

import jakarta.validation.constraints.Email;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {
     private String username;

     private String full_name;

    @Email(message = "Email is invalid")
    private String email;

    private String password;

    private Role role;
}
