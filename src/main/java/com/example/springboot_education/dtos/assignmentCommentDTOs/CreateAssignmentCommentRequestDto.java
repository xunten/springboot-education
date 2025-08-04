package com.example.springboot_education.dtos.assignmentCommentDTOs;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateAssignmentCommentRequestDto {
    private Long assignmentId;
    private Long userId;
    private String comment;
    private Long parentId; // optional
}
