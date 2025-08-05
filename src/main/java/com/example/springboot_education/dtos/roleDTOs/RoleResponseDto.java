
package com.example.springboot_education.dtos.roleDTOs;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.example.springboot_education.dtos.usersDTOs.UserSummaryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto implements Serializable {
    private Long id;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<UserSummaryDto> users; // <- Đừng quên field này
}
