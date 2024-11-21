package com.example.task_management_system.model.response;

import com.example.task_management_system.model.TaskPriority;
import com.example.task_management_system.model.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TaskResponse {
    private String title;
    private String description;
    private String authorName;
    private String executorName;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
}
