package com.example.springboot_education.dtos.roleDTOs;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleRequestDto {

    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

//    @Size(max = 500, message = "Description cannot exceed 500 characters")
//    private String description;

    /**
     * Checks if at least one field is provided for update
     */
    public boolean hasAnyField() {
        return name != null;
    }
}
