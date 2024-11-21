package com.example.task_management_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "task_management-system_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String authorName;
    private String executorName;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @OneToMany(mappedBy = "task",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Comment> comments;

}
