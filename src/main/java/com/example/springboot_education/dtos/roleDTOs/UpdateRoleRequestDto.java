package com.example.springboot_education.dtos.roleDTOs;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for updating role information.
 * Contains validation rules for role updates.
 * Fields are optional to support partial updates - only non-null fields will be
 * updated.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleRequestDto {

    @Size(min = 2, max = 50, message = "Code must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Z][A-Z0-9_]*$", message = "Code must start with uppercase letter and contain only uppercase letters, numbers, and underscores")
    private String code;

    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    /**
     * Checks if at least one field is provided for update
     */
    public boolean hasAnyField() {
        return code != null || name != null || description != null;
    }
}