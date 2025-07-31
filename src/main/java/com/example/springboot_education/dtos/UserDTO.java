package com.example.springboot_education.dtos;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String fullName;
    private String email;
    private Timestamp createdAt;
    // private List<Integer> roleIds;
    private List<String> roleNames;
}
