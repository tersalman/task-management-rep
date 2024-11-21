package com.example.task_management_system.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
//@Table(name = "task_management-system_comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorName;
    private String content;
    @ManyToOne
    @JoinColumn(name =
            "task_id")
    private Task task;
}
