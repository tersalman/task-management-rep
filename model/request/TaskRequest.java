package com.example.task_management_system.model.request;

import com.example.task_management_system.model.TaskPriority;
import com.example.task_management_system.model.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TaskRequest {
    private String title;
    private String email;
    private String description;
    private String executorName;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
}
