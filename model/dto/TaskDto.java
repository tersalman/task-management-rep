package com.example.task_management_system.model.dto;

import com.example.task_management_system.model.Comment;
import com.example.task_management_system.model.TaskPriority;
import com.example.task_management_system.model.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String authorName;
    private String executorName;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
    private Set<CommentDto> comments;
}
