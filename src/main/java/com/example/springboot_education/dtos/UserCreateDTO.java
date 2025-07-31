package com.example.springboot_education.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserCreateDTO {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private List<Integer> roleIds;
}
