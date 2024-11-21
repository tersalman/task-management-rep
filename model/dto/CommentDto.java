package com.example.task_management_system.model.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String authorName;
    private String content;
}
