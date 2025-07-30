package com.example.springboot_education.dtos.classUserDTOs;


import java.sql.Timestamp;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClassUserRequestDto {
  @NotNull(message = "student_id is required")
    private Long student_id;

    @NotNull(message = "class_id is required")
    private Long class_id;

    private Timestamp joined_at;

   
}
