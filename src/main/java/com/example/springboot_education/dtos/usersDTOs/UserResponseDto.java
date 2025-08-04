package com.example.springboot_education.dtos.usersDTOs;
import java.security.Timestamp;
import java.util.List;

import com.example.springboot_education.dtos.roleDTOs.RoleResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    
    @JsonProperty("full_name") 
    private String fullName;
    
    @JsonProperty("image_url") 
    private String imageUrl;
    
    private String email;
    private List<RoleResponseDto> roles;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}