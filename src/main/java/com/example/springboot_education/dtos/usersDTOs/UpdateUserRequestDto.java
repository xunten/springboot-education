package com.example.springboot_education.dtos.usersDTOs;

import com.example.springboot_education.entities.Users.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {
     private String username;

     @JsonProperty("full_name")
     private String fullName;

    @Email(message = "Email is invalid")
    private String email;
    
    @JsonProperty("image_url")
    private String imageUrl;
    
    private String password;

    
    private Role role;
}
