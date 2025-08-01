package com.example.springboot_education.dtos.usersDTOs;

import com.example.springboot_education.entities.Users.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor  
public class CreateUserRequestDto {
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Full name is required")
      @JsonProperty("full_name") 
    private String fullName;  
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;
    
      @JsonProperty("image_url") 
    private String imageUrl;  
    
    @NotBlank(message = "Password is required")
    private String password;
    
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;  
    
    private Role role;
}