package com.example.springboot_education.dtos.classUserDTOs;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClassUserRequestDto {

    private Long classId;
    private Long studentId;
}
