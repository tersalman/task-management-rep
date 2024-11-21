package com.example.task_management_system.model.request;

import lombok.Data;

@Data
public class CommentRequest {

    private String taskTitle;
    private String email;
    private String content;
}
