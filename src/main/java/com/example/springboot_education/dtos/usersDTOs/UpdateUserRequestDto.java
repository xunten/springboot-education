package com.example.springboot_education.dtos.usersDTOs;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,100}$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;
}