package com.example.springboot_education.dtos;

import lombok.Data;

@Data
public class UpdateUserDTO {
    private String fullName;
    private String email;
    private String username;
    // Không cho sửa password nếu chưa cần
}
