package com.example.springboot_education.dtos.usersDTOs;

import com.example.springboot_education.entities.Role;
// import com.example.springboot_education.entities.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Data
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
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,100}$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;
      
}