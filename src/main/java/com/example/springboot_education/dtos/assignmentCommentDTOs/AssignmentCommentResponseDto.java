package com.example.springboot_education.dtos.assignmentCommentDTOs;

import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AssignmentCommentResponseDto {
    private Long id;
    private Long assignmentId;
    private Long userId;
    private String comment;
    private Timestamp createdAt;
    private Long parentId;

    private List<AssignmentCommentResponseDto> replies = new ArrayList<>();
}