package com.example.springboot_education.dtos.usersDTOs;

import lombok.*;

import java.util.List;

@Setter
@Getter
public class UserIdsRequestDto {
    private List<Long> userIds;

}
