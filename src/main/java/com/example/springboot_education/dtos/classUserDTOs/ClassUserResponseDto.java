package com.example.springboot_education.dtos.classUserDTOs;

import java.sql.Timestamp;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassUserResponseDto {
    private Long class_id;
    private Long student_id;
    private Timestamp joined_at;
   
}
