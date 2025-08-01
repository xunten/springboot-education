package com.example.springboot_education.dtos.usersDTOs;
import com.example.springboot_education.entities.Users.Role;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {
    private String username;
    
    @JsonAlias("full_name")
    private String fullName;
    
    @Email(message = "Email is invalid")
    private String email;
    
    @JsonAlias("image_url")
    private String imageUrl;
    
    private Role role;
}