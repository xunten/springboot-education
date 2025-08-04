package com.example.springboot_education.dtos.usersDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;


@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {

    @NotBlank(message = "Name is required")
    private String username;

    @NotBlank(message = "Full name is required")
    private String full_name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @JsonProperty("image_url")
    private String image_url;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,100}$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;

    @NotEmpty(message = "At least one role is required")
    private List<Long> role_id;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
