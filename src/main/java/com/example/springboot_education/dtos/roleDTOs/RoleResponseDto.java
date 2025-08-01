package com.example.springboot_education.dtos.roleDTOs;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for representing user information in API responses.
 * Contains user basic information and associated roles.
 */
@Data
@Builder
@NoArgsConstructor
public class RoleResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;

    public RoleResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}