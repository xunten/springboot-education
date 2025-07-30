package com.example.springboot_education.dtos.usersDTOs;

import com.example.springboot_education.entities.Users;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String username;

   @JsonProperty("full_name")
private String fullName;

    private String email;
    @JsonProperty("image_url")
private String imageUrl;
    private Users.Role role;
}
